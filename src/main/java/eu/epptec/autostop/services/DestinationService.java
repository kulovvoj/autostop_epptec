package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.DestinationNotFoundException;
import eu.epptec.autostop.exceptions.PassengerNotFoundException;
import eu.epptec.autostop.exceptions.RideFullException;
import eu.epptec.autostop.exceptions.UserNotFoundException;
import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.model.Passenger;
import eu.epptec.autostop.model.UserRideDTO;
import eu.epptec.autostop.repositories.DestinationRepository;
import eu.epptec.autostop.repositories.PassengerRepository;
import eu.epptec.autostop.repositories.RideRepository;
import eu.epptec.autostop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService implements IDestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Destination findById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(id));
    }

    @Override
    public Destination save(Destination destination, Long rideId) {
        destination.setRide(rideRepository.getById(rideId));
        return destinationRepository.save(destination);
    }

    @Override
    public Page<Destination> findAll(Long rideId, Pageable pageable) {
        return destinationRepository.findByRideId(rideId, pageable);
    }

    @Override
    public UserRideDTO reserve(Long userId, Long fromId, Long toId) {
        // Get all destinations user will go through
        List<Destination> destinations = destinationRepository.findAllInbetween(fromId, toId);
        // See if any have capacity of < 1, if yes, return error code
        destinations.forEach(destination -> {
            if (destination.getPassengerCount() < 0)
                throw new RideFullException();
        });
        // Update the decrease of the capacity of each of them
        destinations.forEach(destination -> {
            destination.setPassengerCount(destination.getPassengerCount() + 1);
            destinationRepository.save(destination);
        });
        // Save the passenger entity
        Passenger passenger = new Passenger();
        passenger.setFrom(destinationRepository.findById(fromId).orElseThrow(() -> new DestinationNotFoundException(fromId)));
        passenger.setTo(destinationRepository.findById(toId).orElseThrow(() -> new DestinationNotFoundException(toId)));
        passenger.setUser(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        passengerRepository.save(passenger);
        // Return userRideDTO
        return rideRepository.getUserRideDTOByPassenger(userId, fromId, toId);
    }

    @Override
    public void cancelReservation(Long passengerId) {
        // See if the passage actually exists
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new PassengerNotFoundException(passengerId));
        // Get all destinations user will go through
        List<Destination> destinations = destinationRepository.findAllInbetween(passenger.getFrom().getId(),
                passenger.getTo().getId());
        // See if any have capacity of < 1, if yes, return error code
        destinations.forEach(destination -> {
            if (destination.getPassengerCount() < 0)
                throw new RideFullException();
        });
        // Update the decrease of the capacity of each of them
        destinations.forEach(destination -> {
            destination.setPassengerCount(destination.getPassengerCount() - 1);
            destinationRepository.save(destination);
        });
        // Delete the passenger entity
        passengerRepository.deleteById(passengerId);
    }

    @Override
    public Destination replace(Destination destination, Long id) {
        return destinationRepository.findById(id)
                .map(oldDestination -> {
                    oldDestination.setAddress(destination.getAddress());
                    oldDestination.setDepartureTime(destination.getDepartureTime());
                    oldDestination.setPrice(destination.getPrice());
                    oldDestination.setRide(destination.getRide());
                    return destinationRepository.save(destination);
                })
                .orElseGet(() -> {
                    destination.setId(id);
                    return destinationRepository.save(destination);
                });
    }

    @Override
    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }
}
