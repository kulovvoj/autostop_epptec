package eu.epptec.autostop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "id_ride")
    private Ride ride;

    @OneToMany(mappedBy = "from")
    private List<Passenger> passengersEntering;

    @OneToMany(mappedBy = "to")
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
}
