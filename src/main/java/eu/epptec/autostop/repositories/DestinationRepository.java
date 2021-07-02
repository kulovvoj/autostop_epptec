package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Page<Destination> findByRideId(Long rideId, Pageable pageable);
}
