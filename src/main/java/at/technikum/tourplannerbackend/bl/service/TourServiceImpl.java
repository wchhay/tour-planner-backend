package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.mapper.TourMapper;
import at.technikum.tourplannerbackend.bl.dto.TourUpdateDto;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.mapquest.DirectionsAPIService;
import at.technikum.tourplannerbackend.dal.mapquest.MapImageService;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation.Route;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class TourServiceImpl implements TourService {

    private final DirectionsAPIService directionsAPIService;

    private final MapImageService mapImageService;

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(
            DirectionsAPIService directionsAPIService,
            MapImageService mapImageService,
            TourRepository tourRepository
    ) {
        this.directionsAPIService = directionsAPIService;
        this.mapImageService = mapImageService;
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAllTours() {
        logger.info("Retrieving all tours");
        return tourRepository.findAll();
    }

    @Override
    public Tour getById(UUID id) {
        logger.info("Retrieving tour with tourId={}", id);
        return tourRepository.findById(id)
                .orElseThrow(TourNotFoundException::new);
    }

    @Override
    public Tour createAndPersistTour(TourCreationDto tourCreationDto) {
        logger.info("Creating new tour");
        Tour tour = TourMapper.fromDto(tourCreationDto);
        fetchRouteInformationAndImage(tour);

        return tourRepository.save(tour);
    }

    @Override
    public byte[] getMapImage(UUID id) {
        logger.info("Retrieving map image for tourId={}", id);
        Tour tour = getById(id);

        return mapImageService.readFromFile(tour.getImagePath());
    }

    @Override
    public Tour updateTour(UUID id, TourUpdateDto tourUpdateDto) {
        logger.info("Updating tour with tourId={}", id);
        Tour tour = getById(id);
        boolean mapquestRequestRequired = mapquestRequestRequired(tourUpdateDto, tour);

        TourMapper.updateFromDto(tour, tourUpdateDto);

        if (mapquestRequestRequired) {
            logger.info("Re-fetching route information and image from Mapquest API for tourId={}", id);
            mapImageService.deleteImageFile(tour.getImagePath());
            fetchRouteInformationAndImage(tour);
        }

        return tourRepository.save(tour);
    }

    @Override
    public void deleteTour(UUID id) {
        logger.info("Deleting tour with tourId={}", id);
        Tour tour = getById(id);
        mapImageService.deleteImageFile(tour.getImagePath());

        tourRepository.delete(tour);
    }

    private boolean mapquestRequestRequired(TourUpdateDto dto, Tour tour) {
        return isNotNullAndDifferent(dto.getFrom(), tour.getFrom())
                || isNotNullAndDifferent(dto.getTo(), tour.getTo())
                || isNotNullAndDifferent(dto.getTransportType(), tour.getTransportType());
    }

    private <T> boolean isNotNullAndDifferent(T dtoValue, T tourValue) {
        return (null != dtoValue && !dtoValue.equals(tourValue));
    }

    private void fetchRouteInformationAndImage(Tour tour) {
        Route routeResponse = directionsAPIService
                .getRouteInformation(tour.getFrom(), tour.getTo(), tour.getTransportType())
                .getRoute();

        String imagePath = mapImageService.downloadAndSaveMapImage(routeResponse.getSessionId());

        tour.setDistance(routeResponse.getDistance());
        tour.setEstimatedTime(routeResponse.getTime());
        tour.setImagePath(imagePath);
    }
}
