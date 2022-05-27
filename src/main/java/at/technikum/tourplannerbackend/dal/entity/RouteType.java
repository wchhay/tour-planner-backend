package at.technikum.tourplannerbackend.dal.entity;


public enum RouteType {
    BICYCLE("bicycle"),
    PEDESTRIAN("pedestrian"),
    FASTEST("fastest"),
    SHORTEST("shortest");

    public final String value;

    RouteType(String value) {
        this.value = value;
    }
}
