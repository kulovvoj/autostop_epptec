package eu.epptec.autostop.exceptions;

public class RideFullException extends RuntimeException {
    public RideFullException() {
        super("Ride is full");
    }
}