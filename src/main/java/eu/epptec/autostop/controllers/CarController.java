package eu.epptec.autostop.controllers;


import eu.epptec.autostop.dtos.CarDTO;
import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users/{userId}/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping()
    @ResponseStatus(CREATED)
    CarDTO addCar(@RequestBody CarDTO carDTO, @PathVariable Long userId) {
        return carService.save(carDTO, userId);
    }

    @GetMapping()
    Page<CarDTO> findAll(@PathVariable Long userId, Pageable pageable) {
        return carService.findAll(userId, pageable);
    }

    @GetMapping(value = "/active")
    Page<CarDTO> findAllActive(@PathVariable Long userId, Pageable pageable) {
        return carService.findAllActive(userId, pageable);
    }

    @GetMapping("/{carId}")
    CarDTO findById(@PathVariable Long carId) {
        return carService.findById(carId);
    }

    @PutMapping("/{carId}")
    CarDTO replace(@RequestBody CarDTO carDTO, @PathVariable Long carId) {
        return carService.replace(carDTO, carId);
    }

    @DeleteMapping("/{carId}")
    ResponseEntity<Object> deleteById(@PathVariable Long carId) {
        carService.deleteById(carId);

        return ResponseEntity.noContent().build();
    }
}