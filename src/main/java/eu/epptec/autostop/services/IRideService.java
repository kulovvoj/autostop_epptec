package eu.epptec.autostop.services;

import eu.epptec.autostop.controllers.RideController.SearchData;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.model.RideSearchListingDTO;
import eu.epptec.autostop.model.UserRideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRideService {
    Ride findById(Long id);
    Ride save(Ride ride, Long carId);
    Page<UserRideDTO> findPastDriverRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findFutureDriverRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findPastPassengerRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findFuturePassengerRides(Long userId, Pageable pageable);
    void rate(Long passengerId, Integer rating);
    Ride replace(Ride ride, Long id);
    void deleteById(Long id);
    Page<RideSearchListingDTO> getRideSearchListing(SearchData searchData, Pageable pageable);
}
