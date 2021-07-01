package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Car;

import java.util.List;

public interface ICarService {
    Car findById(Long id);
    Car save(Car car);
    List<Car> findAll();
    Car replace(Car car, Long id);
    void deleteById(Long id);
}
