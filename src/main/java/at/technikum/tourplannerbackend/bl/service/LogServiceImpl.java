package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.mapper.LogMapper;
import at.technikum.tourplannerbackend.bl.dto.LogUpdateDto;
import at.technikum.tourplannerbackend.bl.service.exception.LogNotFoundException;
import at.technikum.tourplannerbackend.bl.service.exception.TourIdMismatchException;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Log;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.repository.LogRepository;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    private final TourRepository tourRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, TourRepository tourRepository) {
        this.logRepository = logRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public Log createLog(UUID tourId, LogCreationDto logCreationDto) {
        logger.info("Creating log for tourId={}", tourId);
        Log log = LogMapper.fromDto(logCreationDto);
        Tour tour = tourRepository.findById(tourId).orElseThrow(TourNotFoundException::new);
        log.setTourReference(tour);

        return logRepository.save(log);
    }

    @Override
    public List<Log> getLogsForTour(UUID tourId) {
        logger.info("Retrieving logs for tourId={}", tourId);
        Tour tour = tourRepository.findById(tourId).orElseThrow(TourNotFoundException::new);
        return tour.getLogsList();
    }

    @Override
    public Log getLogById(UUID logId) {
        logger.info("Retrieving log with logId={}", logId);
        return logRepository.findById(logId).orElseThrow(LogNotFoundException::new);
    }

    @Override
    public Log updateLog(UUID tourId, UUID logId, LogUpdateDto logUpdateDto) {
        logger.info("Updating log with logId={}, tourId={}", logId, tourId);
        Log log = getLogById(logId);
        validateMatchingTourId(tourId, log);
        LogMapper.updateLog(log, logUpdateDto);
        return logRepository.save(log);
    }

    @Override
    public void deleteLog(UUID tourId, UUID logId) {
        logger.info("Deleting log with logId={}, tourId={}", logId, tourId);
        Log log = getLogById(logId);
        validateMatchingTourId(tourId, log);
        logRepository.deleteById(logId);
    }

    private void validateMatchingTourId(UUID tourId, Log log) {
        if (log.getTourReference().getId().compareTo(tourId) != 0) {
            throw new TourIdMismatchException();
        }
    }
}
