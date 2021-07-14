package eu.epptec.autostop.model;

import java.sql.Timestamp;

public class UserDriverRideDTO {
    Long rideId;
    Long fromDestinationId;
    String fromCity;
    Timestamp departureTime;
    Long toDestinationId;
    String toCity;
    Timestamp arrivalTime;
    Long carId;
    String carBrand;
    String carModel;
    String carType;
    Integer carProductionYear;
}
