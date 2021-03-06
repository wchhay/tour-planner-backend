package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.config.MapquestConfig;
import at.technikum.tourplannerbackend.dal.entity.TransportType;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DirectionsAPIServiceImpl implements DirectionsAPIService {

    public static final String DIRECTIONS_API_URL = "https://www.mapquestapi.com/directions/v2/route";
    public static final String KILOMETERS = "k";

    private final MapquestConfig mapquestConfig;

    private final RestTemplate restTemplate;

    @Autowired
    public DirectionsAPIServiceImpl(MapquestConfig mapquestConfig, RestTemplate restTemplate) {
        this.mapquestConfig = mapquestConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public RouteInformation getRouteInformation(String from, String to, TransportType transportType) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(DIRECTIONS_API_URL)
                .queryParam("key", mapquestConfig.getApiKey())
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("unit", KILOMETERS)
                .queryParam("routeType", transportType.value);

        return restTemplate.getForObject(uriBuilder.build(), RouteInformation.class);
    }
}
