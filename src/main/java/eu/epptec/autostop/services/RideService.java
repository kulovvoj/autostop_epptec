package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.RideNotFoundException;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService implements IRideService {
    @Autowired
    private RideRepository rideRepository;

    @Override
    public Ride findById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RideNotFoundException(id));
    }

    @Override
    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    @Override
    public Ride replace(Ride ride, Long id) {
        return rideRepository.findById(id)
                .map(oldRide -> {
                    oldRide.setCapacity(ride.getCapacity());
                    oldRide.setCar(ride.getCar());
                    oldRide.setT(ride.getType());
                    oldRide.setProductionYear(ride.getProductionYear());
                    oldRide.setCapacity(ride.getCapacity());
                    oldRide.setUser(ride.getUser());
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
