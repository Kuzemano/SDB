package r.real.model.exceptions;


public class InvalidDriverIdException extends RuntimeException {
    public InvalidDriverIdException() {
        super("Driver with the given ID does not exist.");
    }

    public InvalidDriverIdException(Long id) {
        super("Driver with ID " + id + " does not exist.");
    }
}