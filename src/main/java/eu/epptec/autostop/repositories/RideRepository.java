package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.dtos.RideSearchListingDTO;
import eu.epptec.autostop.dtos.UserRideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query(value = "SELECT new eu.epptec.autostop.dtos.UserRideDTO(r.id, \n" +
            "                                                       dFirst.id, \n" +
            "                                                       dFirst.address.city, \n" +
            "                                                       dFirst.departureTime, \n" +
            "                                                       dLast.id, \n" +
            "                                                       dLast.address.city, \n" +
            "                                                       dLast.departureTime, \n" +
            "                                                       c.id, \n" +
            "                                                       c.brand, \n" +
            "                                                       c.model, \n" +
            "                                                       c.productionYear) \n" +
            "FROM User u \n" +
            "JOIN u.cars c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations d \n" +
            "JOIN r.destinations dFirst \n" +
            "   WITH dFirst.departureTime = (SELECT MIN(dMin.departureTime) \n" +
            "   FROM Destination dMin \n" +
            "   WHERE dMin.ride.id = r.id) \n" +
            "JOIN r.destinations dLast \n" +
            "   WITH dLast.departureTime = (SELECT MAX(dMax.departureTime) \n" +
            "   FROM Destination dMax \n" +
            "   WHERE dMax.ride.id = r.id) \n" +
            "WHERE u.id = :userId \n" +
            "   AND :now >= dFirst.departureTime \n" +
            "GROUP BY r.id \n" +
            "ORDER BY dFirst.departureTime DESC")
    Page<UserRideDTO> findPastDriverRides(@Param("userId") Long userId, @Param("now") Timestamp now, Pageable pageable);

    @Query(value = "SELECT new eu.epptec.autostop.dtos.UserRideDTO(r.id, \n" +
            "                                                       dFirst.id, \n" +
            "                                                       dFirst.address.city, \n" +
            "                                                       dFirst.departureTime, \n" +
            "                                                       dLast.id, \n" +
            "                                                       dLast.address.city, \n" +
            "                                                       dLast.departureTime, \n" +
            "                                                       c.id, \n" +
            "                                                       c.brand, \n" +
            "                                                       c.model, \n" +
            "                                                       c.productionYear) \n" +
            "FROM User u \n" +
            "JOIN u.cars c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations d \n" +
            "JOIN r.destinations dFirst \n" +
            "   WITH dFirst.departureTime = (SELECT MIN(dMin.departureTime) \n" +
            "   FROM Destination dMin \n" +
            "   WHERE dMin.ride.id = r.id) \n" +
            "JOIN r.destinations dLast \n" +
            "   WITH dLast.departureTime = (SELECT MAX(dMax.departureTime) \n" +
            "   FROM Destination dMax \n" +
            "   WHERE dMax.ride.id = r.id) \n" +
            "WHERE u.id = :userId \n" +
            "   AND :now < dFirst.departureTime \n" +
            "GROUP BY r.id \n" +
            "ORDER BY dFirst.departureTime ASC")
    Page<UserRideDTO> findFutureDriverRides(@Param("userId") Long userId, @Param("now") Timestamp now, Pageable pageable);

    @Query(value = "SELECT new eu.epptec.autostop.dtos.UserRideDTO(r.id, \n" +
            "                                                       dFrom.id, \n" +
            "                                                       dFrom.address.city, \n" +
            "                                                       dFrom.departureTime, \n" +
            "                                                       dTo.id, \n" +
            "                                                       dTo.address.city, \n" +
            "                                                       dTo.departureTime, \n" +
            "                                                       c.id, \n" +
            "                                                       c.brand, \n" +
            "                                                       c.model, \n" +
            "                                                       c.productionYear) \n" +
            "FROM User u \n" +
            "JOIN u.passengerOfList p \n" +
            "JOIN p.from dFrom \n" +
            "JOIN p.to dTo \n" +
            "JOIN dTo.ride r \n" +
            "JOIN r.car c \n" +
            "WHERE u.id = :userId \n" +
            "   AND :now < dFrom.departureTime \n" +
            "GROUP BY r.id \n" +
            "ORDER BY dFrom.departureTime ASC")
    Page<UserRideDTO> findPastPassengerRides(@Param("userId") Long userId, @Param("now") Timestamp now, Pageable pageable);

    @Query(value = "SELECT new eu.epptec.autostop.dtos.UserRideDTO(r.id, \n" +
            "                                                       dFrom.id, \n" +
            "                                                       dFrom.address.city, \n" +
            "                                                       dFrom.departureTime, \n" +
            "                                                       dTo.id, \n" +
            "                                                       dTo.address.city, \n" +
            "                                                       dTo.departureTime, \n" +
            "                                                       c.id, \n" +
            "                                                       c.brand, \n" +
            "                                                       c.model, \n" +
            "                                                       c.productionYear) \n" +
            "FROM User u \n" +
            "JOIN u.passengerOfList p \n" +
            "JOIN p.from dFrom \n" +
            "JOIN p.to dTo \n" +
            "JOIN dTo.ride r \n" +
            "JOIN r.car c \n" +
            "WHERE u.id = :userId \n" +
            "   AND :now >= dFrom.departureTime \n" +
            "GROUP BY p.id \n" +
            "ORDER BY dFrom.departureTime DESC")
    Page<UserRideDTO> findFuturePassengerRides(@Param("userId") Long userId, @Param("now") Timestamp now, Pageable pageable);

    @Query(value = "SELECT new eu.epptec.autostop.dtos.RideSearchListingDTO(r.id, \n" +
            "                                                               dFrom.id, \n" +
            "                                                               aFrom.city, \n" +
            "                                                               dFrom.departureTime, \n" +
            "                                                               dTo.id, \n" +
            "                                                               aTo.city, \n" +
            "                                                               dTo.departureTime, \n" +
            "                                                               c.id, \n" +
            "                                                               c.brand, \n" +
            "                                                               c.model, \n" +
            "                                                               c.type, \n" +
            "                                                               c.productionYear," +
            "                                                               CASE WHEN pAvg IS NULL THEN -1 ELSE ROUND(AVG(pAvg.driverRating)) END, \n" +
            "                                                               c.capacity - MAX(dStop.passengerCount), \n" +
            "                                                               SUM(dStop.price)) \n " +
            // The ride and destinations that fulfill the query
            "FROM Car c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations dFrom \n" +
            "   WITH :departureTime IS NULL OR :departureTime < dFrom.departureTime \n" +
            "JOIN dFrom.address aFrom \n" +
            "   WITH lower(aFrom.city) LIKE lower(concat('%', :cityFrom, '%')) \n" +
            "JOIN r.destinations dTo \n" +
            "   WITH dFrom.departureTime < dTo.departureTime \n" +
            "JOIN dTo.address aTo \n" +
            "   WITH lower(aTo.city) LIKE lower(concat('%', :cityTo, '%'))" +
            // Stops in between the from and to destinations, which we need for the price and capacity calculation
            "JOIN r.destinations dStop \n" +
            "   WITH dFrom.departureTime < dStop.departureTime AND dStop.departureTime <= dTo.departureTime \n" +
            // And all the ratings of given user as a driver
            "JOIN c.user u \n" +
            "JOIN u.cars c_avg \n" +
            "JOIN c_avg.rides r_avg \n" +
            "JOIN r_avg.destinations d_avg \n" +
            "LEFT JOIN d_avg.passengersEntering pAvg \n" +
            "GROUP BY dFrom.id, dTo.id \n" +
            "ORDER BY dFrom.departureTime ASC")
    Page<RideSearchListingDTO> getRideSearchListingDeparture(@Param("cityFrom") String cityFrom,
                                                             @Param("cityTo") String cityTo,
                                                             @Param("departureTime") Timestamp departureTime,
                                                             Pageable pageable);

    @Query(value = "SELECT new eu.epptec.autostop.dtos.RideSearchListingDTO(r.id, \n" +
            "                                                               dFrom.id, \n" +
            "                                                               aFrom.city, \n" +
            "                                                               dFrom.departureTime, \n" +
            "                                                               dTo.id, \n" +
            "                                                               aTo.city, \n" +
            "                                                               dTo.departureTime, \n" +
            "                                                               c.id, \n" +
            "                                                               c.brand, \n" +
            "                                                               c.model, \n" +
            "                                                               c.type, \n" +
            "                                                               c.productionYear," +
            "                                                               CASE WHEN pAvg IS NULL THEN -1 ELSE ROUND(AVG(pAvg.driverRating)) END, \n" +
            "                                                               c.capacity - MAX(dStop.passengerCount), \n" +
            "                                                               SUM(dStop.price)) \n " +
            // The ride and destinations that fulfill the query
            "FROM Car c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations dFrom \n" +
            "JOIN dFrom.address aFrom \n" +
            "   WITH lower(aFrom.city) LIKE lower(concat('%', :cityFrom, '%')) \n" +
            "JOIN r.destinations dTo \n" +
            "   WITH dFrom.departureTime < dTo.departureTime \n" +
            "   AND (:arrivalTime IS NULL OR dFrom.departureTime < :arrivalTime) \n" +
            "JOIN dTo.address aTo \n" +
            "   WITH lower(aTo.city) LIKE lower(concat('%', :cityTo, '%'))" +
            // Stops in between the from and to destinations, which we need for the price and capacity calculation
            "JOIN r.destinations dStop \n" +
            "   WITH dFrom.departureTime < dStop.departureTime AND dStop.departureTime <= dTo.departureTime \n" +
            // And all the ratings of given user as a driver
            "JOIN c.user u \n" +
            "JOIN u.cars c_avg \n" +
            "JOIN c_avg.rides r_avg \n" +
            "JOIN r_avg.destinations d_avg \n" +
            "LEFT JOIN d_avg.passengersEntering pAvg \n" +
            "GROUP BY dFrom.id, dTo.id \n" +
            "ORDER BY dTo.departureTime DESC")
    Page<RideSearchListingDTO> getRideSearchListingArrival(@Param("cityFrom") String cityFrom,
                                                           @Param("cityTo") String cityTo,
                                                           @Param("arrivalTime") Timestamp arrivalTime,
                                                           Pageable pageable);


    @Query(value = "SELECT new eu.epptec.autostop.dtos.UserRideDTO(r.id, \n" +
            "                                                       dFrom.id, \n" +
            "                                                       dFrom.address.city, \n" +
            "                                                       dFrom.departureTime, \n" +
            "                                                       dTo.id, \n" +
            "                                                       dTo.address.city, \n" +
            "                                                       dTo.departureTime, \n" +
            "                                                       c.id, \n" +
            "                                                       c.brand, \n" +
            "                                                       c.model, \n" +
            "                                                       c.productionYear) \n" +
            "FROM User u \n" +
            "JOIN u.passengerOfList p \n" +
            "JOIN p.from dFrom \n" +
            "JOIN p.to dTo \n" +
            "JOIN dTo.ride r \n" +
            "JOIN r.car c \n" +
            "WHERE u.id = :userId \n" +
            "   AND dFrom.id = :fromId \n" +
            "   AND dTo.id = :toId \n")
    UserRideDTO getUserRideDTOByPassenger(@Param("userId") Long userId,
                                          @Param("fromId") Long fromId,
                                          @Param("toId") Long toId);

}
