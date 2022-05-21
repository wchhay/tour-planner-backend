package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.config.MapquestConfig;
import at.technikum.tourplannerbackend.dal.mapquest.exception.ImageFileException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URL;

@Service
public class StaticMapAPIService {

    public static final String STATIC_MAP_API_URL = "https://www.mapquestapi.com/staticmap/v5/map";

    private final MapquestConfig mapquestConfig;

    private final ImageFileService imageFileService;

    @Autowired
    public StaticMapAPIService(MapquestConfig mapquestConfig, ImageFileService imageFileService) {
        this.mapquestConfig = mapquestConfig;
        this.imageFileService = imageFileService;
    }

    public String getAndSaveMapImage(String sessionId) {
        try {
            URL url = UriComponentsBuilder.fromHttpUrl(STATIC_MAP_API_URL)
                    .queryParam("key", mapquestConfig.getApiKey())
                    .queryParam("session", sessionId)
                    .build().toUri().toURL();

            var image = imageFileService.readFromURL(url);

            String imagePath = buildImagePath();
            imageFileService.writeToJPGFile(image, imagePath);

            return imagePath;
        } catch (IOException e) {
            throw new ImageFileException();
        }
    }

    private String buildImagePath() {
        return mapquestConfig.getImagesDir() + "/" + RandomStringUtils.randomAlphanumeric(16) + ".jpg";
    }
}
