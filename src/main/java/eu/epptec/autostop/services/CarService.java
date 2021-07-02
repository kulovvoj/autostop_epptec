package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.CarNotFoundException;
import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car save(Car car, Long userId) {
        car.setUser(userRepository.getById(userId));
        return carRepository.save(car);
    }

    @Override
    public Page<Car> findAll(Long userId, Pageable pageable) {
        return carRepository.findByUserId(userId, pageable);
    }

    @Override
    public Car replace(Car car, Long id) {
        return carRepository.findById(id)
                .map(oldCar -> {
                    oldCar.setBrand(car.getBrand());
                    oldCar.setModel(car.getModel());
                    oldCar.setType(car.getType());
                    oldCar.setProductionYear(car.getProductionYear());
                    oldCar.setCapacity(car.getCapacity());
                    oldCar.setUser(car.getUser());
                    return carRepository.save(car);
                })
                .orElseGet(() -> {
                    car.setId(id);
                    return carRepository.save(car);
                });
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
