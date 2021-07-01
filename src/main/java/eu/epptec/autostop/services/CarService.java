package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.CarNotFoundException;
import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
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
