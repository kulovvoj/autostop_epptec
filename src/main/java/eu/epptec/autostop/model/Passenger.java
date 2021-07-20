package eu.epptec.autostop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="passenger")
public class Passenger {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "passenger_rating")
    private int passengerRating;

    @Column(name = "driver_rating")
    private int driverRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination_from")
    private Destination from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination_to")
    private Destination to;

    public Passenger() {
    }

    public Passenger(Long id, int passengerRating, int driverRating) {
        this.id = id;
        this.passengerRating = passengerRating;
        this.driverRating = driverRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Destination getFrom() {
        return from;
    }

    public void setFrom(Destination from) {
        this.from = from;
    }

    public Destination getTo() {
        return to;
    }

    public void setTo(Destination to) {
        this.to = to;
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
