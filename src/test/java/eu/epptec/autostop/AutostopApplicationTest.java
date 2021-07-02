package eu.epptec.autostop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.epptec.autostop.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        assert userEntityModel != null;
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
        assert userEntityModel != null;
        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/cars";
        Car car = new Car("BMW", "M6", "Sedan", 2016, 5, userEntityModel.getContent());
        HttpEntity<Car> carRequest = new HttpEntity<>(car);

        result = this.restTemplate.postForEntity(baseUrl, carRequest, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first Ride 
        // -------------
        EntityModel<Car> carEntityModel = null;
        try {
            carEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert carEntityModel != null;
        baseUrl = "http://localhost:" + randomServerPort + "/rides";
        Ride ride = new Ride(carEntityModel.getContent().getCapacity() - 1, carEntityModel.getContent());

        HttpEntity<Ride> rideRequest = new HttpEntity<>(ride);
        result = this.restTemplate.postForEntity(baseUrl, rideRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

        // -------------
        // Add the first destination of the first ride
        // -------------
        EntityModel<Ride> rideEntityModel = null;
        try {
            rideEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert rideEntityModel != null;

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
        baseUrl = "http://localhost:" + randomServerPort + "/rides";

        rideRequest = new HttpEntity<>(ride);
        result = this.restTemplate.postForEntity(baseUrl, rideRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        // -------------
        // Add the first destination of the second ride
        // -------------
        rideEntityModel = null;
        try {
            rideEntityModel = objectMapper.readValue(result.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert rideEntityModel != null;

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
        // Request all past rides of user
        // -------------

        baseUrl = "http://localhost:" + randomServerPort + "/users/" + userEntityModel.getContent().getId() + "/getPastRides";
        result = this.restTemplate.postForEntity(baseUrl, destinationRequest, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

    }
}