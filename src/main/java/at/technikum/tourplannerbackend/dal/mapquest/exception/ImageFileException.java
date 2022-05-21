package at.technikum.tourplannerbackend.dal.mapquest.exception;

public class ImageFileException extends RuntimeException {

    public static final String MESSAGE = "Cannot download and save image";

    public ImageFileException() {
        super(MESSAGE);
    }
}
