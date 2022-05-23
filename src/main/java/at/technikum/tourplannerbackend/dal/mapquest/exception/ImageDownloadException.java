package at.technikum.tourplannerbackend.dal.mapquest.exception;

public class ImageDownloadException extends RuntimeException {

    public static final String MESSAGE = "Cannot download and save image";

    public ImageDownloadException() {
        super(MESSAGE);
    }
}
