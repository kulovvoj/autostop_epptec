package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRideService {
    Ride findById(Long id);
    Ride save(Ride ride, Long carId);
    Page<Ride> findPastDriverRides(Long userId, Pageable pageable);
    Page<Ride> findFutureDriverRides(Long userId, Pageable pageable);
    Ride replace(Ride ride, Long id);
    void deleteById(Long id);
}
