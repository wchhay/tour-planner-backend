package at.technikum.tourplannerbackend.dal.mapquest.exception;

public class ImageReadException extends RuntimeException {

    public static final String MESSAGE = "Cannot read image.";

    public ImageReadException() {
        super(MESSAGE);
    }
}
