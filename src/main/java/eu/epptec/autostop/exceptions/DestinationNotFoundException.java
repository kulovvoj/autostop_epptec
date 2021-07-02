package eu.epptec.autostop.exceptions;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(Long id) {
        super("Destination with id '" + id + "' not found");
    }
}
