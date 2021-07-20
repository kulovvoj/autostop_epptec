package eu.epptec.autostop.services;

import eu.epptec.autostop.dtos.DestinationDTO;
import eu.epptec.autostop.dtos.UserRideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DestinationService {
    DestinationDTO findById(Long id);
    DestinationDTO save(DestinationDTO destinationDTO, Long rideId);
    Page<DestinationDTO> findAll(Long rideId, Pageable pageable);
    UserRideDTO reserve(Long userId, Long fromId, Long toId);
    void cancelReservation(Long passengerId);
    DestinationDTO replace(DestinationDTO destinationDTO, Long id);
    void deleteById(Long id);
}
