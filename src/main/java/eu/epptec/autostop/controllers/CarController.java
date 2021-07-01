package eu.epptec.autostop.controllers;


import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users/{userId}/cars")
@ComponentScan(basePackageClasses = {CarModelAssembler.class})
public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private CarModelAssembler assembler;

    @PostMapping(consumes = "application/json", produces = "application/json")
    EntityModel<Car> addCar(@RequestBody Car car, @PathVariable Long userId) {
        car = carService.save(car, userId);

        return assembler.toModel(car);
    }

    @GetMapping()
    CollectionModel<EntityModel<Car>> findAll(@PathVariable Long userId, Pageable pageable) {
        List<EntityModel<Car>> cars = carService.findAll(userId, pageable)
                .stream()
                .map(car -> assembler.toModel(car))
                .collect(Collectors.toList());

        return CollectionModel.of(cars,
                linkTo(methodOn(CarController.class).findAll(userId, pageable)).withSelfRel());
    }

    @GetMapping("/{carId}")
    EntityModel<Car> findById(@PathVariable Long carId) {
        Car car = carService.findById(carId);

        return assembler.toModel(car);
    }

    @PutMapping("/{carId}")
    EntityModel<Car> replace(@RequestBody Car car, @PathVariable Long carId) {
        car = carService.replace(car, carId);

        return assembler.toModel(car);
    }

    @DeleteMapping("/{carId}")
    ResponseEntity<Object> deleteById(@PathVariable Long carId) {
        carService.deleteById(carId);

        return ResponseEntity.noContent().build();
    }
}