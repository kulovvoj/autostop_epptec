package eu.epptec.autostop.dtos;

import eu.epptec.autostop.model.Address;

public class AddressDTO {
    private Long id;
    private String city;
    private String zipCode;
    private String street;
    private Integer lrNumber;
    private Integer houseNumber;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String city, String zipCode, String street, Integer lrNumber, Integer houseNumber) {
        this.id = id;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.lrNumber = lrNumber;
        this.houseNumber = houseNumber;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.street = address.getStreet();
        this.lrNumber = address.getLrNumber();
        this.houseNumber = address.getHouseNumber();
    }

    public Address toEntity() {
        return new Address(id, city, zipCode, street, lrNumber, houseNumber);
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
}
