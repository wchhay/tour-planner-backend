package at.technikum.tourplannerbackend.dal.entity;


public enum TransportType {
    BICYCLE("bicycle"),
    PEDESTRIAN("pedestrian"),
    FASTEST("fastest"),
    SHORTEST("shortest");

    public final String value;

    TransportType(String value) {
        this.value = value;
    }
}
