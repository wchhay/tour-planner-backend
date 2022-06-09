package at.technikum.tourplannerbackend.dal.mapquest;

import at.technikum.tourplannerbackend.dal.entity.TransportType;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation;

public interface DirectionsAPIService {
    RouteInformation getRouteInformation(String from, String to, TransportType transportType);
}
