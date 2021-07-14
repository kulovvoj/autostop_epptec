package eu.epptec.autostop.services;

import eu.epptec.autostop.controllers.RideController.SearchData;
import eu.epptec.autostop.exceptions.RideNotFoundException;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.model.RideSearchListingDTO;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.DestinationRepository;
import eu.epptec.autostop.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RideService implements IRideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DestinationRepository destinationRepository;

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
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findPastDriverRides(userId, now, pageable);
    }

    @Override
    public Page<Ride> findFutureDriverRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findFutureDriverRides(userId, now, pageable);
    }

    @Override
    public Page<Ride> findPastPassengerRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findPastPassengerRides(userId, now, pageable);
    }

    @Override
    public Page<Ride> findFuturePassengerRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findFuturePassengerRides(userId, now, pageable);
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

    @Override
    public List<RideSearchListingDTO> getRideSearchListing(SearchData searchData) {
        if (searchData.arrival)
            return rideRepository.getRideSearchListingArrival(searchData.cityFrom, searchData.cityTo, searchData.time);
        else
            return rideRepository.getRideSearchListingDeparture(searchData.cityFrom, searchData.cityTo, searchData.time);
    }
}
