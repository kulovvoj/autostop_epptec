package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.RideNotFoundException;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RideService implements IRideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Ride findById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RideNotFoundException(id));
    }

    @Override
    public Ride save(Ride ride, Long carId) {
        ride.setCar(carRepository.getById(carId));
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> findPastDriverRides(Long userId, Pageable pageable) {
        return rideRepository.findPastDriverRides(userId, pageable);
    }

    @Override
    public Ride replace(Ride ride, Long id) {
        return rideRepository.findById(id)
                .map(oldRide -> {
                    oldRide.setCar(ride.getCar());
                    oldRide.setCapacity(ride.getCapacity());
                    return rideRepository.save(ride);
                })
                .orElseGet(() -> {
                    ride.setId(id);
                    return rideRepository.save(ride);
                });
    }

    @Override
    public void deleteById(Long id) {
        rideRepository.deleteById(id);
    }
}
