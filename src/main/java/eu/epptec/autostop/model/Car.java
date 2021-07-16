package eu.epptec.autostop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference(value = "userCarRef")
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "carRideRef")
    private List<Ride> rides;

    public Car() {
    }

    public Car(Boolean active, String brand, String model, String type, Integer productionYear, Integer capacity, User user) {
        this.active = active;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.productionYear = productionYear;
        this.capacity = capacity;
        this.user = user;
    }

    public Car(Long id, Boolean active, String brand, String model, String type, Integer productionYear, Integer capacity, User user, List<Ride> rides) {
        this.id = id;
        this.active = active;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.productionYear = productionYear;
        this.capacity = capacity;
        this.user = user;
        this.rides = rides;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ride> getTrips() {
        return rides;
    }

    public void setTrips(List<Ride> rides) {
        this.rides = rides;
    }
}
