package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDestinationService {
    Destination findById(Long id);
    Destination save(Destination destination, Long rideId);
    Page<Destination> findAll(Long rideId, Pageable pageable);
    Destination replace(Destination destination, Long id);
    void deleteById(Long id);
}
