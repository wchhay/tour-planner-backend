package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.Log;

public class LogMapper {

    private LogMapper() {
    }

    public static Log fromDto(LogCreationDto dto) {
        return Log.builder()
                .date(dto.getDate())
                .comment(dto.getComment())
                .difficulty(dto.getDifficulty())
                .totalTime(dto.getTotalTime())
                .rating(dto.getRating())
                .build();
    }

    public static LogDto toDto(Log log) {
        return LogDto.builder()
                .id(log.getId())
                .date(log.getDate())
                .comment(log.getComment())
                .totalTime(log.getTotalTime())
                .difficulty(log.getDifficulty())
                .rating(log.getRating())
                .build();
    }
}
