package eu.epptec.autostop.controllers;


import eu.epptec.autostop.model.RatingDTO;
import eu.epptec.autostop.model.RideSearchListingDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RatingDTOModelAssembler implements RepresentationModelAssembler<RatingDTO, EntityModel<RatingDTO>> {
    @Override
    public EntityModel<RatingDTO> toModel (RatingDTO ratingDTO) {
        return EntityModel.of(
                ratingDTO,
                linkTo(methodOn(UserController.class).findById(ratingDTO.getUserId())).withSelfRel());
    }
}
