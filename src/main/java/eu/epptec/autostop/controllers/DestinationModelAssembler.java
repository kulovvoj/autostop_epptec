package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.Destination;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DestinationModelAssembler implements RepresentationModelAssembler<Destination, EntityModel<Destination>> {
    @Override
    public EntityModel<Destination> toModel (Destination destination) {
        return EntityModel.of(
                destination,
                linkTo(methodOn(CarController.class).findById(destination.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).findAll(destination.getRide().getId(), Pageable.ofSize(10))).withRel("rideDestinations"));
    }
}
