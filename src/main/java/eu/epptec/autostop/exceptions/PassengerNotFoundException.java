package eu.epptec.autostop.exceptions;

public class PassengerNotFoundException  extends RuntimeException {
    public PassengerNotFoundException(Long id) {
        super("Passenger with id '" + id + "' not found");
    }
}