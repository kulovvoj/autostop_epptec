package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query(value = "SELECT r " +
            "FROM Destination d " +
            "JOIN d.ride r " +
            "JOIN r.car c " +
            "JOIN c.user u " +
            "WHERE u.id = ?1 " +
            "AND d.departureTime < CURRENT_TIMESTAMP " +
            "GROUP BY r.id " +
            "ORDER BY d.departureTime DESC")
    Page<Ride> findPastDriverRides(Long userId, Pageable pageable);
}
