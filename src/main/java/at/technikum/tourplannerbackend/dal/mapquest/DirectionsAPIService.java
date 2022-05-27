package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.config.MapquestConfig;
import at.technikum.tourplannerbackend.dal.entity.RouteType;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DirectionsAPIService {

    public static final String DIRECTIONS_API_URL = "https://www.mapquestapi.com/directions/v2/route";

    private final MapquestConfig mapquestConfig;

    private final RestTemplate restTemplate;

    @Autowired
    public DirectionsAPIService(MapquestConfig mapquestConfig, RestTemplate restTemplate) {
        this.mapquestConfig = mapquestConfig;
        this.restTemplate = restTemplate;
    }

    public RouteInformation getRouteInformation(String from, String to, RouteType routeType) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(DIRECTIONS_API_URL)
                .queryParam("key", mapquestConfig.getApiKey())
                .queryParam("from", from)
                .queryParam("to", to);

        if (null != routeType) {
            uriBuilder.queryParam("routeType", routeType.value);
        }

        return restTemplate.getForObject(uriBuilder.build(), RouteInformation.class);
    }
}
