package eu.epptec.autostop.services;

import eu.epptec.autostop.controllers.RideController.SearchData;
import eu.epptec.autostop.dtos.RideDTO;
import eu.epptec.autostop.model.Ride;
import eu.epptec.autostop.dtos.RideSearchListingDTO;
import eu.epptec.autostop.dtos.UserRideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RideService {
    RideDTO findById(Long id);
    RideDTO save(RideDTO rideDTO, Long carId);
    Page<UserRideDTO> findPastDriverRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findFutureDriverRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findPastPassengerRides(Long userId, Pageable pageable);
    Page<UserRideDTO> findFuturePassengerRides(Long userId, Pageable pageable);
    void rate(Long passengerId, Integer rating);
    RideDTO replace(RideDTO rideDTO, Long id);
    void deleteById(Long id);
    Page<RideSearchListingDTO> getRideSearchListing(SearchData searchData, Pageable pageable);
}
