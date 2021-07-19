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

    @Query("SELECT d \n" +
            "FROM Ride r \n" +
            "JOIN r.destinations dFrom \n" +
            "   WITH dFrom.id = :fromId \n" +
            "JOIN r.destinations dTo \n" +
            "   WITH dTo.id = :toId \n" +
            "JOIN r.destinations d \n" +
            "WHERE dFrom.departureTime < d.departureTime \n" +
            "   AND d.departureTime <= dTo.departureTime")
    List<Destination> findAllInbetween(@Param("fromId") Long fromId,
                                       @Param("toId") Long toId);
}
