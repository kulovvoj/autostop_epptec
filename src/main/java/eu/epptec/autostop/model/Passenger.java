package eu.epptec.autostop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="passenger")
public class Passenger {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_destination_from")
    private Destination from;

    @ManyToOne
    @JoinColumn(name = "id_destination_to")
    private Destination to;

    @Column(name = "passenger_rating")
    private int passengerRating;

    @Column(name = "driver_rating")
    private int driverRating;

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