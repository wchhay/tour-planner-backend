package at.technikum.tourplannerbackend.dal.mapquest;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageFileService {

    public byte[] readFromFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    public BufferedImage readFromURL(URL url) throws IOException {
        return ImageIO.read(url);
    }

    public void writeToJPGFile(RenderedImage image, String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }
}