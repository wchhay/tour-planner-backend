package at.technikum.tourplannerbackend.dto;

import at.technikum.tourplannerbackend.entity.Tour;

public class TourMapper {

    private TourMapper() {
    }

    public static TourDto mapToDto(Tour tour) {
        return TourDto.builder()
                .route(tour.getRoute())
                .description(tour.getDescription())
                .build();
    }

    public static Tour fromDto(TourCreationDto dto) {
        return Tour.builder()
                .route(dto.getRoute())
                .description(dto.getDescription())
                .build();
    }

}
