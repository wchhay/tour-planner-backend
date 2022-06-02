package at.technikum.tourplannerbackend.bl.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Log not found.";

    public LogNotFoundException() {
        super(MESSAGE);
    }
}
