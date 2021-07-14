package eu.epptec.autostop.services;

import eu.epptec.autostop.controllers.RideController.SearchData;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.model.RideSearchListingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRideService {
    Ride findById(Long id);
    Ride save(Ride ride, Long carId);
    Page<Ride> findPastDriverRides(Long userId, Pageable pageable);
    Page<Ride> findFutureDriverRides(Long userId, Pageable pageable);
    Page<Ride> findPastPassengerRides(Long userId, Pageable pageable);
    Page<Ride> findFuturePassengerRides(Long userId, Pageable pageable);
    Ride replace(Ride ride, Long id);
    void deleteById(Long id);
    List<RideSearchListingDTO> getRideSearchListing(SearchData searchData);
}
