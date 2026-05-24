package r.real.drivers.domain.exceptions;


import r.real.drivers.domain.ids.DriverId;

public class InvalidDriverIdException extends RuntimeException {
    public InvalidDriverIdException() {
        super("Driver with the given ID does not exist.");
    }

    public InvalidDriverIdException(DriverId id) {
        super("Driver with ID " + id + " does not exist.");
    }


}