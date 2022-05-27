package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.Tour;

import java.util.Collections;

public class TourMapper {

    private TourMapper() {
    }

    public static TourDto toDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .name(tour.getName())
                .from(tour.getFrom())
                .to(tour.getTo())
                .transportType(tour.getRouteType().name())
                .distance(tour.getDistance())
                .estimatedTime(tour.getEstimatedTime())
                .description(tour.getDescription())
                .logs(tour.getLogsList().stream().map(LogMapper::toDto).toList())
                .build();
    }

    public static Tour fromDto(TourCreationDto dto) {
        return Tour.builder()
                .name(dto.getName())
                .from(dto.getFrom())
                .to(dto.getTo())
                .routeType(dto.getRouteType())
                .description(dto.getDescription())
                .logsList(Collections.emptyList())
                .build();
    }

}
