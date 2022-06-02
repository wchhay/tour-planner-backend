package at.technikum.tourplannerbackend.dal.mapquest.exception;

public class ImageFileException extends RuntimeException {

    public static final String MESSAGE = "Cannot open image.";

    public ImageFileException() {
        super(MESSAGE);
    }
}
