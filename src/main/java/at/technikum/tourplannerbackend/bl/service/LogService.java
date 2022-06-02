package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.LogMapper;
import at.technikum.tourplannerbackend.bl.service.exception.TourNotFoundException;
import at.technikum.tourplannerbackend.dal.entity.Log;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.repository.LogRepository;
import at.technikum.tourplannerbackend.dal.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogService {

    private final LogRepository logRepository;

    private final TourRepository tourRepository;

    @Autowired
    public LogService(LogRepository logRepository, TourRepository tourRepository) {
        this.logRepository = logRepository;
        this.tourRepository = tourRepository;
    }

    public Log createLog(UUID tourId, LogCreationDto logCreationDto) {
        Log log = LogMapper.fromDto(logCreationDto);
        Tour tour = tourRepository.findById(tourId).orElseThrow(TourNotFoundException::new);
        log.setTourReference(tour);

        return logRepository.save(log);
    }
}
