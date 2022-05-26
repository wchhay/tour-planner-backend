package at.technikum.tourplannerbackend.bl.controller;

import at.technikum.tourplannerbackend.bl.dto.LogCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourDto;
import at.technikum.tourplannerbackend.bl.dto.TourMapper;
import at.technikum.tourplannerbackend.bl.service.LogService;
import at.technikum.tourplannerbackend.bl.service.TourService;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.mapquest.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TourController {

    private final TourService tourService;

    private final LogService logService;

    private final ImageFileService imageFileService;

    @Autowired
    public TourController(TourService tourService, LogService logService, ImageFileService imageFileService) {
        this.tourService = tourService;
        this.logService = logService;
        this.imageFileService = imageFileService;
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
        Tour tour = tourService.getById(id);
        return imageFileService.readFromFile(tour.getImagePath());
    }

    @PostMapping("/tours")
    public TourDto createTour(@RequestBody TourCreationDto tourDto) {
        //TODO: Add validations

        Tour tour = tourService.createAndPersistTour(tourDto);
        return TourMapper.toDto(tour);
    }

    @PostMapping("/tours/{id}/logs")
    public void createLog(@PathVariable("id") UUID id, @RequestBody LogCreationDto logCreationDto) {
        //TODO: Add validations

        logService.createLog(id, logCreationDto);
    }
}
