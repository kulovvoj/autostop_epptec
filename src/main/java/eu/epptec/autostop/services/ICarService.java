package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarService {
    Car findById(Long carId);
    Car save(Car car, Long userId);
    Page<Car> findAll(Long userId, Pageable pageable);
    Car replace(Car car, Long id);
    void deleteById(Long id);
}
