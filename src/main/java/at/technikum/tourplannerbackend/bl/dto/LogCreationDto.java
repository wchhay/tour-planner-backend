package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogCreationDto {
    private LocalDateTime date;
    private String comment;
    private Integer difficulty;
    private Long totalTime;
    private Integer rating;
}
