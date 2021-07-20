package eu.epptec.autostop.dtos;

import eu.epptec.autostop.model.Destination;

import java.sql.Timestamp;

public class DestinationDTO {
    private Long id;
    private Timestamp departureTime;
    private int price;
    private int passengerCount;

    public DestinationDTO() {
    }

    public DestinationDTO(Long id, Timestamp departureTime, int price, int passengerCount) {
        this.id = id;
        this.departureTime = departureTime;
        this.price = price;
        this.passengerCount = passengerCount;
    }

    public DestinationDTO(Destination destination) {
        this.id = destination.getId();
        this.departureTime = destination.getDepartureTime();
        this.price = destination.getPrice();
        this.passengerCount = destination.getPassengerCount();
    }

    public Destination toEntity() {
        return new Destination(id, departureTime, price, passengerCount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
