package eu.epptec.autostop.services;

import eu.epptec.autostop.model.Ride;

import java.util.List;

public interface IRideService {
    Ride findById(Long id);
    Ride save(Ride ride);
    List<Ride> findAll();
    Ride replace(Ride ride, Long id);
    void deleteById(Long id);
}
