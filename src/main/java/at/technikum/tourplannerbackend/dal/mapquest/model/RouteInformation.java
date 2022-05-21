package at.technikum.tourplannerbackend.dal.mapquest.model;

import lombok.Data;


@Data
public class RouteInformation {

    @Data
    public static class Route {
        private Double distance;
        private Long time;
        private String sessionId;
    }

    private Route route;
}
