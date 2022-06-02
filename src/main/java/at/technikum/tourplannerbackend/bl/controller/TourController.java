package at.technikum.tourplannerbackend.bl.controller;

import at.technikum.tourplannerbackend.bl.dto.*;
import at.technikum.tourplannerbackend.bl.service.LogService;
import at.technikum.tourplannerbackend.bl.service.TourService;
import at.technikum.tourplannerbackend.dal.entity.Log;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TourController {

    private final TourService tourService;

    private final LogService logService;

    @Autowired
    public TourController(TourService tourService, LogService logService) {
        this.tourService = tourService;
        this.logService = logService;
    }

    @GetMapping("/tours")
    public List<TourDto> getTours() {
        return tourService.getAllTours().stream().map(TourMapper::toDto).toList();
    }

    @GetMapping("/tours/{id}")
    public TourDto getTourById(@PathVariable("id") UUID id) {
        Tour tour = tourService.getById(id);
        return TourMapper.toDto(tour);
    }

    @GetMapping(
        value = "/tours/{id}/map",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getTourMapImage(@PathVariable("id") UUID id) {
        return tourService.getMapImage(id);
    }

    @PostMapping("/tours")
    public TourDto createTour(@Valid @RequestBody TourCreationDto tourDto) {
        Tour tour = tourService.createAndPersistTour(tourDto);
        return TourMapper.toDto(tour);
    }

    @PostMapping("/tours/{id}/logs")
    public LogDto createLog(@PathVariable("id") UUID id, @Valid @RequestBody LogCreationDto logCreationDto) {
        Log log = logService.createLog(id, logCreationDto);
        return LogMapper.toDto(log);
    }

    @PutMapping("/tours/{id}")
    public TourDto updateTour(@PathVariable("id") UUID id, @Valid @RequestBody TourUpdateDto tourUpdateDto) {
        Tour tour = tourService.updateTour(id, tourUpdateDto);
        return TourMapper.toDto(tour);
    }

    @DeleteMapping("/tours/{id}")
    public void deleteTour(@PathVariable("id") UUID id) {
        tourService.deleteTour(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
