package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.config.MapquestConfig;
import at.technikum.tourplannerbackend.dal.mapquest.exception.ImageDownloadException;
import at.technikum.tourplannerbackend.dal.mapquest.exception.ImageReadException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MapImageService {

    public static final String STATIC_MAP_API_URL = "https://www.mapquestapi.com/staticmap/v5/map";

    private final MapquestConfig mapquestConfig;

    @Autowired
    public MapImageService(MapquestConfig mapquestConfig) {
        this.mapquestConfig = mapquestConfig;
    }

    public void downloadAndSaveMapImage(String sessionId, String imagePath) {
        try {
            URL url = UriComponentsBuilder.fromHttpUrl(STATIC_MAP_API_URL)
                    .queryParam("key", mapquestConfig.getApiKey())
                    .queryParam("session", sessionId)
                    .build().toUri().toURL();

            BufferedImage image = downloadFromURL(url);
            writeToJPGFile(image, imagePath);
        } catch (IOException e) {
            throw new ImageDownloadException();
        }
    }

    public byte[] readFromFile(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new ImageReadException();
        }
    }

    public String buildImagePath() {
        return mapquestConfig.getImagesDir() + "/" + RandomStringUtils.randomAlphanumeric(16) + ".jpg";
    }

    private BufferedImage downloadFromURL(URL url) throws IOException {
        return ImageIO.read(url);
    }

    private void writeToJPGFile(RenderedImage image, String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }
}
