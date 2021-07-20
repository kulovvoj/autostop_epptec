package eu.epptec.autostop.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class RideSearchListingDTO {
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
    Integer rating;
    Integer capacity;
    Long price;

    public RideSearchListingDTO() {
    }

    public RideSearchListingDTO(Long rideId,
                                Long fromDestinationId,
                                String fromCity,
                                Date departureTime,
                                Long toDestinationId,
                                String toCity,
                                Date arrivalTime,
                                Long carId,
                                String carBrand,
                                String carModel,
                                String carType,
                                Integer carProductionYear,
                                Integer rating,
                                Integer capacity,
                                Long price) {
        this.rideId = rideId;
        this.fromDestinationId = fromDestinationId;
        this.fromCity = fromCity;
        this.departureTime = new Timestamp(departureTime.getTime());
        this.toDestinationId = toDestinationId;
        this.toCity = toCity;
        this.arrivalTime = new Timestamp(arrivalTime.getTime());
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carType = carType;
        this.carProductionYear = carProductionYear;
        this.rating = rating;
        this.capacity = capacity;
        this.price = price;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getFromDestinationId() {
        return fromDestinationId;
    }

    public void setFromDestinationId(Long fromDestinationId) {
        this.fromDestinationId = fromDestinationId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Long getToDestinationId() {
        return toDestinationId;
    }

    public void setToDestinationId(Long toDestinationId) {
        this.toDestinationId = toDestinationId;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getCarProductionYear() {
        return carProductionYear;
    }

    public void setCarProductionYear(Integer carProductionYear) {
        this.carProductionYear = carProductionYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
