package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourMapper;
import at.technikum.tourplannerbackend.bl.dto.TourUpdateDto;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.mapquest.DirectionsAPIService;
import at.technikum.tourplannerbackend.dal.mapquest.MapImageService;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation.Route;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TourService {

    private final DirectionsAPIService directionsAPIService;

    private final MapImageService mapImageService;

    private final TourRepository tourRepository;

    private final TaskExecutor executor;

    @Autowired
    public TourService(
            DirectionsAPIService directionsAPIService,
            MapImageService mapImageService,
            TourRepository tourRepository,
            @Qualifier("threadPoolTaskExecutor") TaskExecutor executor
    ) {
        this.directionsAPIService = directionsAPIService;
        this.mapImageService = mapImageService;
        this.tourRepository = tourRepository;
        this.executor = executor;
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getById(UUID id) {
        return tourRepository.findById(id)
                .orElseThrow(TourNotFoundException::new);
    }

    public Tour createAndPersistTour(TourCreationDto tourCreationDto) {
        Tour tour = TourMapper.fromDto(tourCreationDto);
        fetchRouteInformationAndImage(tour);

        return tourRepository.save(tour);
    }

    public byte[] getMapImage(UUID id) {
        Tour tour = getById(id);

        return mapImageService.readFromFile(tour.getImagePath());
    }

    public Tour updateTour(UUID id, TourUpdateDto tourUpdateDto) {
        Tour tour = getById(id);
        TourMapper.updateFromDto(tour, tourUpdateDto);

        if (mapquestRequestNeeded(tourUpdateDto)) {
            mapImageService.deleteImageFile(tour.getImagePath());
            fetchRouteInformationAndImage(tour);
        }

        return tourRepository.save(tour);
    }

    public void deleteTour(UUID id) {
        Tour tour = getById(id);
        mapImageService.deleteImageFile(tour.getImagePath());

        tourRepository.delete(tour);
    }

    private boolean mapquestRequestNeeded(TourUpdateDto tourUpdateDto) {
        return null != tourUpdateDto.getFrom() || null != tourUpdateDto.getTo();
    }

    private void fetchRouteInformationAndImage(Tour tour) {
        Route routeResponse = directionsAPIService
                .getRouteInformation(tour.getFrom(), tour.getTo(), tour.getTransportType())
                .getRoute();

        // run image download in background
        String imagePath = mapImageService.buildImagePath();
        executor.execute(() -> mapImageService.downloadAndSaveMapImage(routeResponse.getSessionId(), imagePath));

        tour.setDistance(routeResponse.getDistance());
        tour.setEstimatedTime(routeResponse.getTime());
        tour.setImagePath(imagePath);
    }
}
