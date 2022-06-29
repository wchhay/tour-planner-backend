package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.config.MapquestConfig;
import at.technikum.tourplannerbackend.dal.mapquest.exception.ImageDownloadException;
import at.technikum.tourplannerbackend.dal.mapquest.exception.ImageFileException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@Service
public class MapImageServiceImpl implements MapImageService {

    public static final String STATIC_MAP_API_URL = "https://www.mapquestapi.com/staticmap/v5/map";

    private final MapquestConfig mapquestConfig;

    @Autowired
    public MapImageServiceImpl(MapquestConfig mapquestConfig) {
        this.mapquestConfig = mapquestConfig;
    }

    @Override
    public String downloadAndSaveMapImage(String sessionId) {
        String imagePath = buildImagePath();
        downloadAndSaveImage(imagePath, sessionId);

        return imagePath;
    }

    @Override
    public byte[] readFromFile(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new ImageFileException();
        }
    }

    @Override
    public void deleteImageFile(String path) {
        try {
            if (!Files.deleteIfExists(Paths.get(path))) {
                logger.info("Trying to not existing file, path={}", path);
            }
        } catch (IOException e) {
            throw new ImageFileException();
        }
    }

    @Override
    public String buildImagePath() {
        return mapquestConfig.getImagesDir() + "/" + RandomStringUtils.randomAlphanumeric(16) + ".jpg";
    }

    private void downloadAndSaveImage(String imagePath, String sessionId) {
        try {
            URL url = UriComponentsBuilder.fromHttpUrl(STATIC_MAP_API_URL)
                    .queryParam("key", mapquestConfig.getApiKey())
                    .queryParam("session", sessionId)
                    .build().toUri().toURL();
            RenderedImage image = downloadFromURL(url);
            writeToJPGFile(image, imagePath);
        } catch (IOException e) {
            throw new ImageDownloadException();
        }
    }

    private RenderedImage downloadFromURL(URL url) throws IOException {
        return ImageIO.read(url);
    }

    private void writeToJPGFile(RenderedImage image, String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }
}
