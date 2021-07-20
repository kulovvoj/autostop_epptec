package eu.epptec.autostop.services;

import eu.epptec.autostop.controllers.RideController.SearchData;
import eu.epptec.autostop.dtos.RideDTO;
import eu.epptec.autostop.exceptions.PassengerNotFoundException;
import eu.epptec.autostop.exceptions.RideNotFoundException;
import eu.epptec.autostop.model.Passenger;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.dtos.RideSearchListingDTO;
import eu.epptec.autostop.dtos.UserRideDTO;
import eu.epptec.autostop.repositories.CarRepository;
import eu.epptec.autostop.repositories.DestinationRepository;
import eu.epptec.autostop.repositories.PassengerRepository;
import eu.epptec.autostop.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public RideDTO findById(Long id) {
        return new RideDTO(rideRepository.findById(id)
                .orElseThrow(() -> new RideNotFoundException(id)));
    }

    @Override
    public RideDTO save(RideDTO rideDTO, Long carId) {
        Ride ride = rideDTO.toEntity();
        ride.setCar(carRepository.getById(carId));
        return new RideDTO(rideRepository.save(ride));
    }

    @Override
    public Page<UserRideDTO> findPastDriverRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findPastDriverRides(userId, now, pageable);
    }

    @Override
    public Page<UserRideDTO> findFutureDriverRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findFutureDriverRides(userId, now, pageable);
    }

    @Override
    public Page<UserRideDTO> findPastPassengerRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findPastPassengerRides(userId, now, pageable);
    }

    @Override
    public Page<UserRideDTO> findFuturePassengerRides(Long userId, Pageable pageable) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return rideRepository.findFuturePassengerRides(userId, now, pageable);
    }

    @Override
    public void rate(Long passengerId, Integer rating) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(()
                -> new PassengerNotFoundException(passengerId));
        passenger.setDriverRating(rating);
        passengerRepository.save(passenger);
    }

    @Override
    public RideDTO replace(RideDTO rideDTO, Long id) {
        return rideRepository.findById(id)
                .map(oldRide -> {
                    oldRide.setCapacity(rideDTO.getCapacity());
                    return new RideDTO(rideRepository.save(oldRide));
                })
                .orElseGet(() -> {
                    rideDTO.setId(id);
                    return new RideDTO(rideRepository.save(rideDTO.toEntity()));
                });
    }

    @Override
    public void deleteById(Long id) {
        rideRepository.deleteById(id);
    }

    @Override
    public Page<RideSearchListingDTO> getRideSearchListing(SearchData searchData, Pageable pageable) {
        if (searchData.arrival)
            return rideRepository.getRideSearchListingArrival(searchData.cityFrom, searchData.cityTo, searchData.time, pageable);
        else
            return rideRepository.getRideSearchListingDeparture(searchData.cityFrom, searchData.cityTo, searchData.time, pageable);
    }

}
