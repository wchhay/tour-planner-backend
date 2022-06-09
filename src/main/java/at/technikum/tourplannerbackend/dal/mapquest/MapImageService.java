package at.technikum.tourplannerbackend.dal.mapquest;

public interface MapImageService {
    String downloadAndSaveMapImage(String sessionId);

    byte[] readFromFile(String path);

    void deleteImageFile(String path);

    String buildImagePath();
}
