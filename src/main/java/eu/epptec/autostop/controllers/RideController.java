package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.services.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@ComponentScan(basePackageClasses = {RideModelAssembler.class})
public class RideController {
    @Autowired
    private IRideService rideService;

    @Autowired
    private RideModelAssembler assembler;

    @PostMapping("/rides")
    EntityModel<Ride> addRide(@RequestBody Ride ride) {
        ride = rideService.save(ride);

        return assembler.toModel(ride);
    }

    @GetMapping("/rides/{rideId}")
    EntityModel<Ride> findById(@PathVariable Long rideId) {
        Ride ride = rideService.findById(rideId);

        return assembler.toModel(ride);
    }

    @PutMapping("/rides/{rideId}")
    EntityModel<Ride> replace(@RequestBody Ride ride, @PathVariable Long rideId) {
        ride = rideService.replace(ride, rideId);

        return assembler.toModel(ride);
    }

    @DeleteMapping("/rides/{rideId}")
    ResponseEntity<Object> deleteById(@PathVariable Long rideId) {
        rideService.deleteById(rideId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/pastRides")
    PagedModel<EntityModel<Ride>> findPast(@PathVariable Long userId, Pageable pageable) {
        Page<Ride> rides = rideService.findPastDriverRides(userId, pageable);

        return null;
    }
}