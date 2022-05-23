package at.technikum.tourplannerbackend.bl.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TourNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Tour not found.";

    public TourNotFoundException() {
        super(MESSAGE);
    }
}
