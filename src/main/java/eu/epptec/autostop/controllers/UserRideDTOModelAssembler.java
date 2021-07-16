package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.UserRideDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRideDTOModelAssembler implements RepresentationModelAssembler<UserRideDTO, EntityModel<UserRideDTO>> {
    @Override
    public EntityModel<UserRideDTO> toModel (UserRideDTO userRideDTO) {
        return EntityModel.of(
                userRideDTO,
                linkTo(methodOn(RideController.class).findById(userRideDTO.getRideId())).withSelfRel());
    }
}