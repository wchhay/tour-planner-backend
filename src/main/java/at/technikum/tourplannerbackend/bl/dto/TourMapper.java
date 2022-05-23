package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.Tour;

public class TourMapper {

    private TourMapper() {
    }

    public static TourDto mapToDto(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .from(tour.getFrom())
                .to(tour.getTo())
                .transportType(tour.getTransportType().name())
                .distance(tour.getDistance())
                .estimatedTime(tour.getEstimatedTime())
                .description(tour.getDescription())
                .build();
    }

    public static Tour fromDto(TourCreationDto dto) {
        return Tour.builder()
                .name(dto.getName())
                .from(dto.getFrom())
                .to(dto.getTo())
                .transportType(dto.getTransportType())
                .description(dto.getDescription())
                .build();
    }

}
