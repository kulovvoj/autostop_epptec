package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Destination;
import eu.epptec.autostop.model.UserRideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDestinationService {
    Destination findById(Long id);
    Destination save(Destination destination, Long rideId);
    Page<Destination> findAll(Long rideId, Pageable pageable);
    UserRideDTO reserve(Long userId, Long fromId, Long toId);
    void cancelReservation(Long passengerId);
    Destination replace(Destination destination, Long id);
    void deleteById(Long id);
}
