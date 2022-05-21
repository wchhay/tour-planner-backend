package at.technikum.tourplannerbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("mapquest")
public class MapquestConfig {
    private String apiKey;
    private String imagesDir;
}
