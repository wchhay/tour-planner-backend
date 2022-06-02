package at.technikum.tourplannerbackend.bl.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TourIdMismatchException extends RuntimeException {

    public static final String MESSAGE = "Mismatching Tour Identifier.";

    public TourIdMismatchException() {
        super(MESSAGE);
    }
}
