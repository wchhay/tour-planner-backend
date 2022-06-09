package at.technikum.tourplannerbackend.bl.service;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.LogUpdateDto;
import at.technikum.tourplannerbackend.dal.entity.Log;

import java.util.UUID;

public interface LogService {
    Log createLog(UUID tourId, LogCreationDto logCreationDto);

    Log getLogById(UUID logId);

    Log updateLog(UUID tourId, UUID logId, LogUpdateDto logUpdateDto);

    void deleteLog(UUID tourId, UUID logId);
}
