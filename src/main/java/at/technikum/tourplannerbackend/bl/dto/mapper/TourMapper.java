package at.technikum.tourplannerbackend.bl.dto.mapper;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourDto;
import at.technikum.tourplannerbackend.bl.dto.TourUpdateDto;
import at.technikum.tourplannerbackend.dal.entity.Tour;

import java.util.Collections;

import static at.technikum.tourplannerbackend.bl.dto.mapper.MapperUtils.setIfNotNull;

public class TourMapper {

    private TourMapper() {
    }

    public static TourDto toDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .name(tour.getName())
                .from(tour.getFrom())
                .to(tour.getTo())
                .transportType(tour.getTransportType().name())
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
                .transportType(dto.getTransportType())
                .description(dto.getDescription())
                .logsList(Collections.emptyList())
                .build();
    }

    public static void updateFromDto(Tour tour, TourUpdateDto dto) {
        setIfNotNull(dto.getName(), tour::setName);
        setIfNotNull(dto.getFrom(), tour::setFrom);
        setIfNotNull(dto.getTo(), tour::setTo);
        setIfNotNull(dto.getTransportType(), tour::setTransportType);
        setIfNotNull(dto.getDescription(), tour::setDescription);
    }
}
