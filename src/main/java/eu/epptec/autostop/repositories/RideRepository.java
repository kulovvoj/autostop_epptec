package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;


@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query(value = "SELECT r \n" +
            "FROM Destination d \n" +
            "JOIN d.ride r \n" +
            "JOIN r.car c \n" +
            "JOIN c.user u \n" +
            "WHERE u.id = ?1 \n" +
            "AND d.departureTime < ?2 \n" +
            "GROUP BY r.id \n" +
            "ORDER BY d.departureTime DESC")
    Page<Ride> findPastDriverRides(Long userId, Timestamp now, Pageable pageable);

    @Query(value = "SELECT case WHEN ?2 < ?1 THEN true ELSE false END FROM Destination d")
    boolean test(Timestamp now, Timestamp other);

    @Query(value = "SELECT r \n" +
            "FROM User u \n" +
            "JOIN u.cars c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations d \n" +
            "WHERE u.id = ?1 \n" +
            "AND ?2 < ( SELECT MIN(d_ni.departureTime) \n" +
                "FROM Destination d_ni \n" +
                "WHERE d_ni.ride.id = r.id) \n" +
            "GROUP BY r.id \n" +
            "ORDER BY d.departureTime ASC")
/*
    @Query(value = "SELECT r \n" +
            "FROM Destination d \n" +
            "JOIN d.ride r \n" +
            "JOIN r.car c \n" +
            "JOIN c.user u \n" +
            "WHERE u.id = ?1 \n" +
            "AND NOT EXISTS ( SELECT d_ne \n" +
                "FROM Destination d_ne \n" +
                "WHERE d_ne.ride.id = r.id \n" +
                "AND d.departureTime < ?2 ) \n" +
            "GROUP BY r.id \n" +
            "ORDER BY d.departureTime ASC")*/
    Page<Ride> findFutureDriverRides(Long userId, Timestamp now, Pageable pageable);
}
