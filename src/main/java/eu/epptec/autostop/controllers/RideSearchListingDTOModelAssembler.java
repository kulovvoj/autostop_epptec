package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.model.RideSearchListingDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RideSearchListingDTOModelAssembler implements RepresentationModelAssembler<RideSearchListingDTO, EntityModel<RideSearchListingDTO>> {
    @Override
    public EntityModel<RideSearchListingDTO> toModel (RideSearchListingDTO rideSearchListingDTO) {
        return EntityModel.of(
                rideSearchListingDTO,
                linkTo(methodOn(RideController.class).findById(rideSearchListingDTO.getRideId())).withSelfRel());
    }
}
