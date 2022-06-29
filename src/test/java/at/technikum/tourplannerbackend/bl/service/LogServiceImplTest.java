package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.LogUpdateDto;
import at.technikum.tourplannerbackend.bl.service.exception.TourIdMismatchException;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Log;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.repository.LogRepository;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Mock
    private LogRepository logRepository;

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private LogServiceImpl logService;

    @Captor
    private ArgumentCaptor<Log> logArgumentCaptor;

    private UUID tourId;
    private UUID logId;
    private UUID otherTourId;
    private Tour tour;
    private Log log;

    @BeforeEach
    void setUp() {
        tourId = UUID.randomUUID();
        otherTourId = UUID.randomUUID();
        logId = UUID.randomUUID();
        log = buildLog(logId);
        tour = buildTour(tourId, log);
        log.setTourReference(tour);

        lenient().when(tourRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        lenient().when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        lenient().when(logRepository.findById(logId)).thenReturn(Optional.of(log));
    }

    @Test
    void GIVEN_tour_exists_WHEN_createLog_THEN_log_should_have_tour_reference() {
        LogCreationDto dto = new LogCreationDto();

        logService.createLog(tourId, dto);

        verify(logRepository, times(1)).save(logArgumentCaptor.capture());
        assertThat(logArgumentCaptor.getValue().getTourReference()).isEqualTo(tour);
    }

    @Test
    void GIVEN_tour_exists_WHEN_getLogsForTour_THEN_should_return_correct_logs() {
        List<Log> logs = logService.getLogsForTour(tourId);

        assertThat(logs).contains(log);
    }

    @Test
    void GIVEN_tour_does_not_exist_WHEN_getLogsForTour_THEN_should_throw_exception() {
        assertThatCode(() -> logService.getLogsForTour(otherTourId))
                .isInstanceOf(TourNotFoundException.class);
    }

    @Test
    void GIVEN_log_matches_with_provided_tourId_WHEN_updateLog_THEN_new_log_persisted() {
        logService.updateLog(tourId, logId, new LogUpdateDto());

        verify(logRepository, times(1)).save(log);
    }

    @Test
    void GIVEN_log_does_not_match_with_provided_tourId_WHEN_updateLog_THEN_exception_thrown() {
        LogUpdateDto dto = new LogUpdateDto();
        assertThatCode(() -> logService.updateLog(otherTourId, logId, dto))
                .isInstanceOf(TourIdMismatchException.class);
    }

    @Test
    void GIVEN_log_matches_with_provided_tourId_WHEN_deleteLog_THEN_log_deleted() {
        logService.deleteLog(tourId, logId);

        verify(logRepository, times(1)).deleteById(logId);
    }

    @Test
    void GIVEN_log_does_not_match_with_provided_tourId_WHEN_deleteLog_THEN_exception_thrown() {
        assertThatCode(() -> logService.deleteLog(otherTourId, logId))
                .isInstanceOf(TourIdMismatchException.class);
    }

    private Tour buildTour(UUID tourId, Log log) {
        return Tour.builder()
                .id(tourId)
                .logsList(List.of(log))
                .build();
    }

    private Log buildLog(UUID logId) {
        return Log.builder()
                .id(logId)
                .build();
    }
}