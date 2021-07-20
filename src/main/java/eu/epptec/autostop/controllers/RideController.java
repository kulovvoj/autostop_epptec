package eu.epptec.autostop.controllers;

import eu.epptec.autostop.dtos.RideDTO;
import eu.epptec.autostop.dtos.RideSearchListingDTO;
import eu.epptec.autostop.dtos.UserRideDTO;
import eu.epptec.autostop.services.DestinationService;
import eu.epptec.autostop.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private DestinationService destinationService;

    @PostMapping("users/{userId}/cars/{carId}/rides")
    RideDTO addRide(@RequestBody RideDTO ride, @PathVariable Long carId) {
        return rideService.save(ride, carId);
    }

    @GetMapping("/rides/{rideId}")
    RideDTO findById(@PathVariable Long rideId) {
        return rideService.findById(rideId);
    }

    @PutMapping("/rides/{rideId}")
    RideDTO replace(@RequestBody RideDTO ride, @PathVariable Long rideId) {
        return rideService.replace(ride, rideId);
    }

    @DeleteMapping("/rides/{rideId}")
    ResponseEntity<Object> deleteById(@PathVariable Long rideId) {
        rideService.deleteById(rideId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/pastDriverRides")
    Page<UserRideDTO> findPastDriverRides(@PathVariable Long userId, Pageable pageable) {
         return rideService.findPastDriverRides(userId, pageable);
    }

    @GetMapping("/users/{userId}/futureDriverRides")
    Page<UserRideDTO> findFutureDriverRides(@PathVariable Long userId, Pageable pageable) {
         return rideService.findFutureDriverRides(userId, pageable);
    }

    @GetMapping("/users/{userId}/pastPassengerRides")
    Page<UserRideDTO> findPastPassengerRides(@PathVariable Long userId, Pageable pageable) {
        return rideService.findPastDriverRides(userId, pageable);
    }

    @GetMapping("/users/{userId}/futurePassengerRides")
    Page<UserRideDTO> findFuturePassengerRides(@PathVariable Long userId, Pageable pageable) {
        return rideService.findFutureDriverRides(userId, pageable);
    }

    @PostMapping("/rides/rideSearchData")
    Page<RideSearchListingDTO> rideSearchData(@RequestBody SearchData searchData, Pageable pageable) {
        return rideService.getRideSearchListing(searchData, pageable);
    }

    @PostMapping("/rides/{rideId}/reservation")
    UserRideDTO reserve(@RequestBody DestinationData destinationData, @PathVariable Long rideId) {
        return destinationService.reserve(destinationData.userId, destinationData.destinationFromId, destinationData.destinationToId);
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