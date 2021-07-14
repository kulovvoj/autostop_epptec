package eu.epptec.autostop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="ride")
public class Ride {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "ride")
    @JsonManagedReference(value = "rideDestRef")
    private List<Destination> destinations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    @JsonBackReference(value = "carRideRef")
    private Car car;

    public Ride() {
    }

    public Ride(int capacity, Car car) {
        this.capacity = capacity;
        this.car = car;
    }

    public Ride(Long id, int capacity, List<Destination> destinations, Car car) {
        this.id = id;
        this.capacity = capacity;
        this.destinations = destinations;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
