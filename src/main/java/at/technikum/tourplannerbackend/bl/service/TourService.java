package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourMapper;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.mapquest.DirectionsAPIService;
import at.technikum.tourplannerbackend.dal.mapquest.StaticMapAPIService;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation.Route;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final DirectionsAPIService directionsAPIService;

    private final StaticMapAPIService staticMapAPIService;

    private final TourRepository tourRepository;

    @Autowired
    public TourService(DirectionsAPIService directionsAPIService, StaticMapAPIService staticMapAPIService, TourRepository tourRepository) {
        this.directionsAPIService = directionsAPIService;
        this.staticMapAPIService = staticMapAPIService;
        this.tourRepository = tourRepository;
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getById(Long id) {
        return tourRepository.getById(id);
    }

    public Tour createAndPersistTour(TourCreationDto tourCreationDto) {
        Tour tour = TourMapper.fromDto(tourCreationDto);

        Route routeResponse = directionsAPIService.getRouteInformation(tour.getFrom(), tour.getTo()).getRoute();

        String imagePath = staticMapAPIService.getAndSaveMapImage(routeResponse.getSessionId());

        tour.setDistance(routeResponse.getDistance());
        tour.setEstimatedTime(routeResponse.getTime());
        tour.setImagePath(imagePath);

        tourRepository.save(tour);

        return tour;
    }
}