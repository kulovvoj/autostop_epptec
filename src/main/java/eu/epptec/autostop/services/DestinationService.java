package eu.epptec.autostop.services;

import eu.epptec.autostop.exceptions.DestinationNotFoundException;
import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.repositories.DestinationRepository;
import eu.epptec.autostop.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DestinationService implements IDestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private RideRepository rideRepository;

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
