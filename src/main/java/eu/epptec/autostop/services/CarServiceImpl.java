package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.CarDTO;
import eu.epptec.autostop.exceptions.CarNotFoundException;
import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarDTO findById(Long id) {
        return new CarDTO(carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id)));
    }

    @Override
    public CarDTO save(CarDTO carDTO, Long userId) {
        Car car = carDTO.toEntity();
        car.setUser(userRepository.getById(userId));
        return new CarDTO(carRepository.save(car));
    }

    @Override
    public Page<CarDTO> findAll(Long userId, Pageable pageable) {
        return carRepository.findByUserId(userId, pageable).map(car -> new CarDTO(car));
    }

    @Override
    public Page<CarDTO> findAllActive(Long userId, Pageable pageable) {
        return carRepository.findByUserIdAndActiveTrue(userId, pageable).map(car -> new CarDTO(car));
    }

    @Override
    public CarDTO replace(CarDTO carDTO, Long id) {
        return carRepository.findById(id)
                .map(oldCar -> {
                    oldCar.setBrand(carDTO.getBrand());
                    oldCar.setModel(carDTO.getModel());
                    oldCar.setType(carDTO.getType());
                    oldCar.setProductionYear(carDTO.getProductionYear());
                    oldCar.setCapacity(carDTO.getCapacity());
                    return new CarDTO(carRepository.save(oldCar));
                })
                .orElseGet(() -> {
                    Car car = carDTO.toEntity();
                    car.setId(id);
                    return new CarDTO(carRepository.save(car));
                });
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
