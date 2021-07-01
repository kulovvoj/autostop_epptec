package eu.epptec.autostop.repositories;

import eu.epptec.autostop.model.Car;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
