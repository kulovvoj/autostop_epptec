package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.CarDTO;
import eu.epptec.autostop.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDTO findById(Long carId);
    CarDTO save(CarDTO carDTO, Long userId);
    Page<CarDTO> findAll(Long userId, Pageable pageable);
    Page<CarDTO> findAllActive(Long userId, Pageable pageable);
    CarDTO replace(CarDTO carDTO, Long id);
    void deleteById(Long id);
}
