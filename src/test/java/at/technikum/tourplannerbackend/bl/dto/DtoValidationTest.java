package at.technikum.tourplannerbackend.bl.dto;


import at.technikum.tourplannerbackend.dal.entity.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DtoValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void test_valid_logCreationDto() {
        LogCreationDto dto = new LogCreationDto();
        dto.setTotalTime(1000L);
        dto.setDifficulty(2);
        dto.setRating(3);
        dto.setDate(LocalDateTime.now());

        var violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    void test_invalid_logCreationDto() {
        LogCreationDto dto = new LogCreationDto();

        var violations = validator.validate(dto);

        assertThat(violations).hasSize(4);
    }

    @Test
    void test_valid_logUpdateDto() {
        LogUpdateDto dto = new LogUpdateDto();
        dto.setDifficulty(2);
        dto.setRating(3);

        var violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    void test_invalid_logUpdateDto() {
        LogUpdateDto dto = new LogUpdateDto();
        dto.setDifficulty(6);
        dto.setRating(5);

        var violations = validator.validate(dto);

        assertThat(violations).hasSize(1);
    }

    @Test
    void test_valid_tourCreationDto() {
        TourCreationDto dto = new TourCreationDto();
        dto.setName("name");
        dto.setFrom("from");
        dto.setTo("to");
        dto.setTransportType(TransportType.FASTEST);

        var violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    void test_invalid_tourCreationDto() {
        TourCreationDto dto = new TourCreationDto();

        var violations = validator.validate(dto);

        assertThat(violations).hasSize(4);
    }
}