package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourUpdateDto;
import at.technikum.tourplannerbackend.dal.entity.Tour;

import java.util.List;
import java.util.UUID;

public interface TourService {
    List<Tour> getAllTours();

    Tour getById(UUID id);

    Tour createAndPersistTour(TourCreationDto tourCreationDto);

    byte[] getMapImage(UUID id);

    Tour updateTour(UUID id, TourUpdateDto tourUpdateDto);

    void deleteTour(UUID id);
}
