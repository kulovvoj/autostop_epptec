package eu.epptec.autostop.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "car")
    private List<Ride> rides;

    public Car() {
    }

    public Car(String brand, String model, String type, int productionYear, int capacity, User user) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.productionYear = productionYear;
        this.capacity = capacity;
        this.user = user;
    }

    public Car(Long id, String brand, String model, String type, int productionYear, int capacity, User user, List<Ride> rides) {
        this.id = id;
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

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
