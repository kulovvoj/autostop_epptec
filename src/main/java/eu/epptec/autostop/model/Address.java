package eu.epptec.autostop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "street")
    private String street;

    @Column(name = "lr_number")
    private Integer lrNumber;

    @Column(name = "house_number")
    private Integer houseNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination")
    private Destination destination;

    public Address() {
    }

    public Address(String city, String zipCode, String street, Integer lrNumber, Integer houseNumber) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.lrNumber = lrNumber;
        this.houseNumber = houseNumber;
    }

    public Address(Long id, String city, String zipCode, String street, Integer lrNumber, Integer houseNumber, Destination destination) {
        this.id = id;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.lrNumber = lrNumber;
        this.houseNumber = houseNumber;
        this.destination = destination;
    }

    public Address(Long id, String city, String zipCode, String street, Integer lrNumber, Integer houseNumber) {
        this.id = id;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.lrNumber = lrNumber;
        this.houseNumber = houseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getLrNumber() {
        return lrNumber;
    }

    public void setLrNumber(Integer lrNumber) {
        this.lrNumber = lrNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
