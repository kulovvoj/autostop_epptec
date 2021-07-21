package eu.epptec.autostop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "price")
    private int price;

    @Column(name = "passenger_count")
    private int passengerCount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "destination", cascade = ALL)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ride")
    private Ride ride;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "from")
    private List<Passenger> passengersEntering;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to")
    private List<Passenger> passengersLeaving;

    public Destination() {
    }

    public Destination(Timestamp departureTime, int price, Address address, Ride ride) {
        this.departureTime = departureTime;
        this.price = price;
        this.address = address;
        this.ride = ride;
    }

    public Destination(Long id, Timestamp departureTime, int price, Address address, Ride ride, List<Passenger> passengersEntering, List<Passenger> passengersLeaving) {
        this.id = id;
        this.departureTime = departureTime;
        this.price = price;
        this.address = address;
        this.ride = ride;
        this.passengersEntering = passengersEntering;
        this.passengersLeaving = passengersLeaving;
    }

    public Destination(Long id, Timestamp departureTime, int price, int passengerCount) {
        this.id = id;
        this.departureTime = departureTime;
        this.price = price;
        this.passengerCount = passengerCount;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public List<Passenger> getPassengersEntering() {
        return passengersEntering;
    }

    public void setPassengersEntering(List<Passenger> passengersEntering) {
        this.passengersEntering = passengersEntering;
    }

    public List<Passenger> getPassengersLeaving() {
        return passengersLeaving;
    }

    public void setPassengersLeaving(List<Passenger> passengersLeaving) {
        this.passengersLeaving = passengersLeaving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return price == that.price && passengerCount == that.passengerCount && Objects.equals(id, that.id) && Objects.equals(departureTime, that.departureTime) && Objects.equals(address, that.address) && Objects.equals(ride, that.ride) && Objects.equals(passengersEntering, that.passengersEntering) && Objects.equals(passengersLeaving, that.passengersLeaving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, price, passengerCount, address, ride, passengersEntering, passengersLeaving);
    }
}
