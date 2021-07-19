package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("SELECT p \n" +
            "FROM Passenger p \n" +
            "JOIN p.user u \n" +
            "   WITH u.id = :userId \n" +
            "JOIN p.from dFrom \n" +
            "   WITH dFrom.id = :fromId \n" +
            "JOIN p.to dTo \n" +
            "   WITH dTo.id = :toId")
    Passenger getPassengerByUserAndDestinations(@Param("userId") Long userId, @Param("fromId") Long fromId, @Param("toId") Long toId);
}
