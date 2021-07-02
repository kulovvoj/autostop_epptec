package eu.epptec.autostop.controllers;

import eu.epptec.autostop.model.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {
    @Override
    public EntityModel<Car> toModel (Car car) {
        return EntityModel.of(
                car,
                linkTo(methodOn(CarController.class).findById(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).findAll(car.getUser().getId(), Pageable.ofSize(10))).withRel("cars"));
    }
}
