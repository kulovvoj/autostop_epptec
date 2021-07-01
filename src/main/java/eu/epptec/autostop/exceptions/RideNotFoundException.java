package eu.epptec.autostop.exceptions;

public class RideNotFoundException extends RuntimeException {
    public RideNotFoundException(Long id) {
        super("Ride with id '" + id + "' not found");
    }
}
