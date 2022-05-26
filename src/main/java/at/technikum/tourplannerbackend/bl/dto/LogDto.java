package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {
    private UUID id;
    private LocalDateTime date;
    private String comment;
    private Long totalTime;
    private Integer difficulty;
    private Integer rating;
}
