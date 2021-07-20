package eu.epptec.autostop.model;

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
    private Integer capacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ride")
    private List<Destination> destinations;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car car;

    public Ride() {
    }

    public Ride(Integer capacity, Car car) {
        this.capacity = capacity;
        this.car = car;
    }

    public Ride(Long id, Integer capacity, List<Destination> destinations, Car car) {
        this.id = id;
        this.capacity = capacity;
        this.destinations = destinations;
        this.car = car;
    }

    public Ride(Long id, Integer capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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
