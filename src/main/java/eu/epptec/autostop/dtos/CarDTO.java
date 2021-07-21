package eu.epptec.autostop.dtos;

import eu.epptec.autostop.model.Car;

import java.util.Objects;

public class CarDTO {
    private Long id;
    private Boolean active;
    private String brand;
    private String model;
    private String type;
    private Integer productionYear;
    private Integer capacity;

    public CarDTO() {
    }

    public CarDTO(Long id, Boolean active, String brand, String model, String type, Integer productionYear, Integer capacity) {
        this.id = id;
        this.active = active;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.productionYear = productionYear;
        this.capacity = capacity;
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.active = car.getActive();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.type = car.getType();
        this.productionYear = car.getProductionYear();
        this.capacity = car.getCapacity();
    }

    public Car toEntity() {
        return new Car(id, active, brand, model, type, productionYear, capacity);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id) && Objects.equals(active, carDTO.active) && Objects.equals(brand, carDTO.brand) && Objects.equals(model, carDTO.model) && Objects.equals(type, carDTO.type) && Objects.equals(productionYear, carDTO.productionYear) && Objects.equals(capacity, carDTO.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, brand, model, type, productionYear, capacity);
    }
}
