package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.Ride;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RideModelAssembler implements RepresentationModelAssembler<Ride, EntityModel<Ride>> {
    @Override
    public EntityModel<Ride> toModel (Ride ride) {
        return EntityModel.of(
                ride,
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).findById(ride.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll()).withRel("rides"));
    }
}