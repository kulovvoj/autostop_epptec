package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.DestinationDTO;
import eu.epptec.autostop.exceptions.DestinationNotFoundException;
import eu.epptec.autostop.exceptions.PassengerNotFoundException;
import eu.epptec.autostop.exceptions.RideFullException;
import eu.epptec.autostop.exceptions.UserNotFoundException;
import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.model.Passenger;
import eu.epptec.autostop.dtos.UserRideDTO;
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
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public DestinationDTO findById(Long id) {
        return new DestinationDTO(destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(id)));
    }

    @Override
    public DestinationDTO save(DestinationDTO destinationDTO, Long rideId) {
        Destination destination = destinationDTO.toEntity();
        destination.setRide(rideRepository.getById(rideId));
        return new DestinationDTO(destinationRepository.save(destination));
    }

    @Override
    public Page<DestinationDTO> findAll(Long rideId, Pageable pageable) {
        return destinationRepository.findByRideId(rideId, pageable).map(destination -> new DestinationDTO(destination));
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
    public DestinationDTO replace(DestinationDTO destinationDTO, Long id) {
        return destinationRepository.findById(id)
                .map(oldDestination -> {
                    oldDestination.setDepartureTime(destinationDTO.getDepartureTime());
                    oldDestination.setPrice(destinationDTO.getPrice());
                    return new DestinationDTO(destinationRepository.save(oldDestination));
                })
                .orElseGet(() -> {
                    destinationDTO.setId(id);
                    return new DestinationDTO(destinationRepository.save(destinationDTO.toEntity()));
                });
    }

    @Override
    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }
}
