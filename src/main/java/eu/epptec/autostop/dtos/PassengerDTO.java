package eu.epptec.autostop.dtos;

import eu.epptec.autostop.model.Passenger;

public class PassengerDTO {
    private Long id;
    private int passengerRating;
    private int driverRating;

    public PassengerDTO() {
    }

    public PassengerDTO(Long id, int passengerRating, int driverRating) {
        this.id = id;
        this.passengerRating = passengerRating;
        this.driverRating = driverRating;
    }

    public PassengerDTO(Passenger passenger) {
        this.id = passenger.getId();
        this.passengerRating = passenger.getPassengerRating();
        this.driverRating = passenger.getDriverRating();
    }

    public Passenger toEntity(Passenger passenger) {
        return new Passenger(id, passengerRating, driverRating);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPassengerRating() {
        return passengerRating;
    }

    public void setPassengerRating(int passengerRating) {
        this.passengerRating = passengerRating;
    }

    public int getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(int driverRating) {
        this.driverRating = driverRating;
    }
}
