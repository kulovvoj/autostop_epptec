package eu.epptec.autostop.repositories;

import eu.epptec.autostop.dtos.RatingDTO;
import eu.epptec.autostop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new eu.epptec.autostop.dtos.RatingDTO(u.id, CASE WHEN pAvg IS NULL THEN -1 ELSE ROUND(AVG(p.driverRating)) END) \n" +
            "FROM User u \n" +
            "JOIN u.cars c \n" +
            "JOIN c.rides r \n" +
            "JOIN r.destinations d \n" +
            "LEFT JOIN d.passengersEntering p \n" +
            "WHERE u.id = :userId")
    RatingDTO getRating(@Param("userId") Long id);
}
