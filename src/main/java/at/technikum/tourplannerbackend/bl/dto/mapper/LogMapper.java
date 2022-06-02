package at.technikum.tourplannerbackend.bl.dto.mapper;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.LogDto;
import at.technikum.tourplannerbackend.bl.dto.LogUpdateDto;
import at.technikum.tourplannerbackend.dal.entity.Log;

import static at.technikum.tourplannerbackend.bl.dto.mapper.MapperUtils.setIfNotNull;

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

    public static void updateLog(Log log, LogUpdateDto dto) {
        setIfNotNull(dto.getDate(), log::setDate);
        setIfNotNull(dto.getComment(), log::setComment);
        setIfNotNull(dto.getTotalTime(), log::setTotalTime);
        setIfNotNull(dto.getDifficulty(), log::setDifficulty);
        setIfNotNull(dto.getRating(), log::setRating);
    }
}
