package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Page<Destination> findByRideId(Long rideId, Pageable pageable);

    @Query("SELECT d " +
            "FROM Ride r " +
            "JOIN r.destinations dFrom " +
            "   WITH dFrom.id = :fromId" +
            "JOIN r.destinations dTo " +
            "   WITH dTo.id = :toId" +
            "JOIN r.destinations d " +
            "WHERE dFrom.departureTime < d.departureTime " +
            "   AND d.departureTime <= dTo.departureTime")
    List<Destination> findAllInbetween(@Param("fromId") Long fromId,
                                       @Param("toId") Long toId);
}
