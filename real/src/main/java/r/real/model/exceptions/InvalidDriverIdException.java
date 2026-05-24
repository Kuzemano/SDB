package r.real.model.exceptions;


import r.real.model.DriverId;

public class InvalidDriverIdException extends RuntimeException {
    public InvalidDriverIdException() {
        super("Driver with the given ID does not exist.");
    }

    public InvalidDriverIdException(DriverId id) {
        super("Driver with ID " + id + " does not exist.");
    }


}