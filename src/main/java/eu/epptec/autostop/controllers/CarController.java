package eu.epptec.autostop.controllers;


import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/cars")
@ComponentScan(basePackageClasses = {CarModelAssembler.class})
public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private CarModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Car> pagedResourcesAssembler;

    @PostMapping()
    EntityModel<Car> addCar(@RequestBody Car car, @PathVariable Long userId) {
        car = carService.save(car, userId);

        return assembler.toModel(car);
    }

    @GetMapping()
    PagedModel<EntityModel<Car>> findAll(@PathVariable Long userId, Pageable pageable) {
        Page<Car> cars = carService.findAll(userId, pageable);

        return pagedResourcesAssembler.toModel(cars, assembler);
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