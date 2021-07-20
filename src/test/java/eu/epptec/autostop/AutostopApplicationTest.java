package eu.epptec.autostop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epptec.autostop.dtos.RideSearchListingDTO;
import eu.epptec.autostop.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import static eu.epptec.autostop.controllers.RideController.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutostopApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    void testAddUser() throws URISyntaxException {
        String baseUrl = "http://localhost:" + randomServerPort + "/users";
        URI uri = new URI(baseUrl);
        User user = new User("Vojtěch", "Kulovaný", "vojtech.kulovany@seznam.cz", "608626271");

        HttpEntity<User> userRequest = new HttpEntity<>(user);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, userRequest, String.class);
        System.out.println(result);

        Assert.assertEquals(200, result.getStatusCodeValue());

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel<User> userEntityModel = null;
        try {
            userEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<User>>(){});
            System.out.println("Test");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(userEntityModel);
        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId();
        this.restTemplate.delete(baseUrl, null, String.class);

    }


    @Test
    void testRideAndPastRidesQuery() throws URISyntaxException {

        // -------------
        // Add the user 
        // -------------
        String baseUrl = "http://localhost:" + randomServerPort + "/users";
        URI uri = new URI(baseUrl);
        User user = new User("Vojtěch", "Kulovaný", "vojtech.kulovany@seznam.cz", "608626271");

        HttpEntity<User> userRequest = new HttpEntity<>(user);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, userRequest, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the car 
        // -------------

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel<User> userEntityModel = null;
        try {
            userEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(userEntityModel);
        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/cars";
        Car car = new Car(true, "BMW", "M6", "Sedan", 2016, 5, userEntityModel.getContent());
        HttpEntity<Car> carRequest = new HttpEntity<>(car);

        result = this.restTemplate.postForEntity(baseUrl, carRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first Ride 
        // -------------
        EntityModel<Car> carEntityModel = null;
        try {
            carEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<Car>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(carEntityModel);
        baseUrl = "http://localhost:" + randomServerPort +
                "/users/" + userEntityModel.getContent().getId() +
                "/cars/" + carEntityModel.getContent().getId() + "/rides";

        Ride ride = new Ride();
        ride.setCapacity(carEntityModel.getContent().getCapacity() - 1);

        HttpEntity<Ride> rideRequest = new HttpEntity<>(ride);

        result = this.restTemplate.postForEntity(baseUrl, rideRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first destination of the first ride
        // -------------

        EntityModel<Ride> rideEntityModel = null;
        try {
            rideEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<Ride>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rideEntityModel);

        baseUrl = "http://localhost:" + randomServerPort + "/rides/" + rideEntityModel.getContent().getId() + "/destinations";
        Address address = new Address("Praha", "169 00", "Ječná", 38, null);
        Destination destination = new Destination(new Timestamp(1625162400000L), 0, address, rideEntityModel.getContent());

        HttpEntity<Destination> destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the second destination of the first ride
        // -------------
        address = new Address("Český Krumlov", "381 01", "Urbinská", 32, null);
        destination = new Destination(new Timestamp(1625259600000L), 200, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the second Ride
        // -------------
        ride = new Ride(carEntityModel.getContent().getCapacity() - 2, carEntityModel.getContent());
        baseUrl = "http://localhost:" + randomServerPort +
                "/users/" + userEntityModel.getContent().getId() +
                "/cars/" + carEntityModel.getContent().getId() +
                "/rides";

        ride = new Ride();
        ride.setCapacity(carEntityModel.getContent().getCapacity() - 1);

        rideRequest = new HttpEntity<>(ride);
        result = this.restTemplate.postForEntity(baseUrl, rideRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first destination of the second ride
        // -------------
        rideEntityModel = null;
        try {
            rideEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<Ride>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rideEntityModel);

        baseUrl = "http://localhost:" + randomServerPort + "/rides/" + rideEntityModel.getContent().getId() + "/destinations";
        address = new Address("Brno", "262 00", "Lanova", 148, 25);
        destination = new Destination(new Timestamp(1623763800000L), 0, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());


        // -------------
        // Add the second destination of the second ride
        // -------------
        address = new Address("Jihlava", "333 03", "Arménská", 226, 22);
        destination = new Destination(new Timestamp(1623769200000L), 150, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the third destination of the second ride
        // -------------
        address = new Address("Jindřichův Hradec", "432 01", "Simonova", 128, null);
        destination = new Destination(new Timestamp(1623774300000L), 100, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the another car
        // -------------
        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/cars";
        car = new Car(true, "Corvette", "C3", "Two-seater", 1973, 2, userEntityModel.getContent());
        carRequest = new HttpEntity<>(car);

        result = this.restTemplate.postForEntity(baseUrl, carRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the third Ride
        // -------------
        carEntityModel = null;
        try {
            carEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<Car>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(carEntityModel);

        baseUrl = "http://localhost:" + randomServerPort +
                "/users/" + userEntityModel.getContent().getId() +
                "/cars/" + carEntityModel.getContent().getId() +
                "/rides";

        ride = new Ride();
        ride.setCapacity(carEntityModel.getContent().getCapacity() - 1);

        rideRequest = new HttpEntity<>(ride);
        result = this.restTemplate.postForEntity(baseUrl, rideRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first destination of the third ride
        // -------------
        rideEntityModel = null;
        try {
            rideEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<EntityModel<Ride>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rideEntityModel);

        baseUrl = "http://localhost:" + randomServerPort + "/rides/" + rideEntityModel.getContent().getId() + "/destinations";
        address = new Address("Písek", "123 00", "Jižní", 20, 25);
        destination = new Destination(new Timestamp(System.currentTimeMillis() + 20000000), 0, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());


        // -------------
        // Add the second destination of the third ride
        // -------------
        address = new Address("Jihlava", "333 03", "Arménská", 226, 22);
        destination = new Destination(new Timestamp(System.currentTimeMillis() + 1000000), 150, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the third destination of the third ride
        // -------------
        address = new Address("Jindřichův Hradec", "432 01", "Simonova", 128, null);
        destination = new Destination(new Timestamp(System.currentTimeMillis() + 2000000), 100, address, rideEntityModel.getContent());

        destinationRequest = new HttpEntity<>(destination);
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Request all past rides of user
        // -------------

        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/pastDriverRides";
        result = this.restTemplate.getForEntity(baseUrl, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        System.out.println(result.getBody());

        // -------------
        // Request all future rides of user
        // -------------

        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/futureDriverRides";
        result = this.restTemplate.getForEntity(baseUrl, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        System.out.println(result.getBody());
    }

    @Test
    void testFindActiveCars() throws URISyntaxException {
        String baseUrl = "http://localhost:" + randomServerPort + "/users/121/cars/136";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        PagedModel<EntityModel<Car>> cars = null;
        try {
            cars = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(cars);
        cars.getContent().forEach(carEntityModel -> System.out.println(carEntityModel.getContent().getModel()));
    }

    @Test
    void testRideSearch() throws URISyntaxException {

        String baseUrl = "http://localhost:" + randomServerPort + "/rides/rideSearchData";
        URI uri = new URI(baseUrl);

        SearchData searchData = new SearchData();

        Timestamp departureTime = new Timestamp(System.currentTimeMillis());
        searchData.cityFrom = "Jihlava";
        searchData.cityTo = "Jindřichův Hradec";
        searchData.time = departureTime;
        searchData.arrival = true;

        HttpEntity<SearchData> searchDataRequest = new HttpEntity<>(searchData);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, searchData, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        Page<RideSearchListingDTO> rideSearchListingDTOs = null;
        try {
            rideSearchListingDTOs = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rideSearchListingDTOs);

        rideSearchListingDTOs.getContent().forEach(rideSearchListingDTO -> {
            System.out.println("___________");
            System.out.println(rideSearchListingDTO.getRideId());
            System.out.println(rideSearchListingDTO.getFromCity());
            System.out.println(rideSearchListingDTO.getDepartureTime());
            System.out.println(rideSearchListingDTO.getToCity());
            System.out.println(rideSearchListingDTO.getArrivalTime());
            System.out.println(rideSearchListingDTO.getRating());
        });
    }
}