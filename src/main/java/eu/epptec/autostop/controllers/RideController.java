package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.*;
import eu.epptec.autostop.services.IDestinationService;
import eu.epptec.autostop.services.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@ComponentScan(basePackageClasses = {RideModelAssembler.class})
public class RideController {
    @Autowired
    private IRideService rideService;

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private RideModelAssembler rideAssembler;

    @Autowired
    private RideSearchListingDTOModelAssembler rideSearchListingDTOModelAssembler;

    @Autowired
    private UserRideDTOModelAssembler userRideDTOAssembler;

    @Autowired
    private PagedResourcesAssembler<Ride> ridePagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<UserRideDTO> userRideDTOPagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<RideSearchListingDTO> rideSearchListingDTOPagedResourcesAssembler;

    @PostMapping("users/{userId}/cars/{carId}/rides")
    EntityModel<Ride> addRide(@RequestBody Ride ride, @PathVariable Long carId) {
        ride = rideService.save(ride, carId);

        return rideAssembler.toModel(ride);
    }

    @GetMapping("/rides/{rideId}")
    EntityModel<Ride> findById(@PathVariable Long rideId) {
        Ride ride = rideService.findById(rideId);

        return rideAssembler.toModel(ride);
    }

    @PutMapping("/rides/{rideId}")
    EntityModel<Ride> replace(@RequestBody Ride ride, @PathVariable Long rideId) {
        ride = rideService.replace(ride, rideId);

        return rideAssembler.toModel(ride);
    }

    @DeleteMapping("/rides/{rideId}")
    ResponseEntity<Object> deleteById(@PathVariable Long rideId) {
        rideService.deleteById(rideId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/pastDriverRides")
    PagedModel<EntityModel<UserRideDTO>> findPastDriverRides(@PathVariable Long userId, Pageable pageable) {
        Page<UserRideDTO> rides = rideService.findPastDriverRides(userId, pageable);
        return userRideDTOPagedResourcesAssembler.toModel(rides, userRideDTOAssembler);
    }

    @GetMapping("/users/{userId}/futureDriverRides")
    PagedModel<EntityModel<UserRideDTO>> findFutureDriverRides(@PathVariable Long userId, Pageable pageable) {
        Page<UserRideDTO> rides = rideService.findFutureDriverRides(userId, pageable);
        return userRideDTOPagedResourcesAssembler.toModel(rides, userRideDTOAssembler);
    }

    @GetMapping("/users/{userId}/pastPassengerRides")
    PagedModel<EntityModel<UserRideDTO>> findPastPassengerRides(@PathVariable Long userId, Pageable pageable) {
        Page<UserRideDTO> rides = rideService.findPastDriverRides(userId, pageable);
        return userRideDTOPagedResourcesAssembler.toModel(rides, userRideDTOAssembler);
    }

    @GetMapping("/users/{userId}/futurePassengerRides")
    PagedModel<EntityModel<UserRideDTO>> findFuturePassengerRides(@PathVariable Long userId, Pageable pageable) {
        Page<UserRideDTO> rides = rideService.findFutureDriverRides(userId, pageable);
        return userRideDTOPagedResourcesAssembler.toModel(rides, userRideDTOAssembler);
    }

    @PostMapping("/rides/rideSearchData")
    PagedModel<EntityModel<RideSearchListingDTO>> rideSearchData(@RequestBody SearchData searchData, Pageable pageable) {
        Page<RideSearchListingDTO> rides = rideService.getRideSearchListing(searchData, pageable);
        return rideSearchListingDTOPagedResourcesAssembler.toModel(rides, rideSearchListingDTOModelAssembler);
    }

    @PostMapping("/rides/{rideId}/reservation")
    EntityModel<UserRideDTO> reserve(@RequestBody DestinationData destinationData, @PathVariable Long rideId) {
        return userRideDTOAssembler.toModel(destinationService.reserve(destinationData.userId, destinationData.destinationFromId, destinationData.destinationToId));
    }

    @PostMapping("/rating")
    void rate(@Param("passengerId") Long passengerId, @Param("rating") Integer rating) {
        rideService.rate(passengerId, rating);
    }

    public static class SearchData {
        public String cityFrom;
        public String cityTo;
        public Timestamp time;
        public Boolean arrival;
    }

    public static class DestinationData {
        public Long userId;
        public Long destinationFromId;
        public Long destinationToId;
    }
}