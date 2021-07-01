package eu.epptec.autostop.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super("Car with id '" + id + "' not found");
    }
}
