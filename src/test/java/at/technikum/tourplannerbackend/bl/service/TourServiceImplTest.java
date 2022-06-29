package at.technikum.tourplannerbackend.bl.service;


import at.technikum.tourplannerbackend.bl.dto.TourUpdateDto;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.entity.TransportType;
import at.technikum.tourplannerbackend.dal.mapquest.DirectionsAPIService;
import at.technikum.tourplannerbackend.dal.mapquest.MapImageService;
import at.technikum.tourplannerbackend.dal.mapquest.model.RouteInformation;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TourServiceImplTest {

    @Mock
    private DirectionsAPIService directionsAPIService;

    @Mock
    private MapImageService mapImageService;

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourServiceImpl tourService;

    private UUID tourId;
    private UUID otherTourId;
    private Tour tour;

    @BeforeEach
    void setUp() {
        tourId = UUID.randomUUID();
        otherTourId = UUID.randomUUID();
        tour = buildTour(tourId);

        lenient().when(tourRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        lenient().when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        lenient().when(directionsAPIService.getRouteInformation(anyString(), anyString(), any(TransportType.class)))
                .thenReturn(buildRouteInformation());

    }

    @Test
    void GIVEN_tour_exists_WHEN_getById_THEN_return_correct_tour() {
        Tour foundTour = tourService.getById(tourId);

        assertThat(foundTour).isEqualTo(tour);
    }

    @Test
    void GIVEN_tour_does_not_exist_WHEN_getById_THEN_should_throw_exception() {
        assertThatCode(() -> tourService.getById(otherTourId))
                .isInstanceOf(TourNotFoundException.class);
    }

    @Test
    void GIVEN_update_tour_destination_WHEN_updateTour_THEN_should_make_request_to_mapquest_api() {
        String newDestination = "new destination";
        TourUpdateDto dto = new TourUpdateDto();
        dto.setTo(newDestination);

        tourService.updateTour(tourId, dto);

        verify(directionsAPIService, times(1))
                .getRouteInformation(anyString(), eq(newDestination), any(TransportType.class));
        verify(mapImageService, times(1)).downloadAndSaveMapImage(anyString());
    }

    @Test
    void GIVEN_to_from_and_transportType_not_set_in_update_dto_WHEN_updateTour_THEN_should_not_make_request_to_mapquest_api() {
        TourUpdateDto dto = new TourUpdateDto();

        tourService.updateTour(tourId, dto);

        verify(directionsAPIService, never())
                .getRouteInformation(anyString(), anyString(), any(TransportType.class));
        verify(mapImageService, never()).downloadAndSaveMapImage(anyString());
    }

    private Tour buildTour(UUID tourId) {
        return Tour.builder()
                .id(tourId)
                .from("from")
                .to("to")
                .transportType(TransportType.FASTEST)
                .build();
    }

    private RouteInformation buildRouteInformation() {
        RouteInformation routeInformation = new RouteInformation();
        routeInformation.setRoute(new RouteInformation.Route());
        routeInformation.getRoute().setSessionId("sessionId");

        return routeInformation;
    }
}