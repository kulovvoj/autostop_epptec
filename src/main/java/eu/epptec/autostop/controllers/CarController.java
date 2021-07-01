package eu.epptec.autostop.controllers;


import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
@ComponentScan(basePackageClasses = {CarModelAssembler.class})
public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private CarModelAssembler assembler;

    @PostMapping(consumes = "application/json", produces = "application/json")
    EntityModel<Car> addCar(@RequestBody Car car) {
        car = carService.save(car);

        return assembler.toModel(car);
    }

    @GetMapping()
    CollectionModel<EntityModel<Car>> findAll() {
        List<EntityModel<Car>> cars = carService.findAll()
                .stream()
                .map(car -> assembler.toModel(car))
                .collect(Collectors.toList());

        return CollectionModel.of(cars,
                linkTo(methodOn(CarController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<Car> findById(@PathVariable Long id) {
        Car car = carService.findById(id);

        return assembler.toModel(car);
    }

    @PutMapping("/cars/{id}")
    EntityModel<Car> replace(@RequestBody Car car, @PathVariable Long id) {
        car = carService.replace(car, id);

        return assembler.toModel(car);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteById(@PathVariable Long id) {
        carService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}