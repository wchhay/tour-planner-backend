package at.technikum.tourplannerbackend.bl.tour;

import at.technikum.tourplannerbackend.bl.dto.TourCreationDto;
import at.technikum.tourplannerbackend.bl.dto.TourDto;
import at.technikum.tourplannerbackend.bl.dto.TourMapper;
import at.technikum.tourplannerbackend.bl.service.TourService;
import at.technikum.tourplannerbackend.dal.entity.Tour;
import at.technikum.tourplannerbackend.dal.mapquest.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {

    private final TourService tourService;

    private final ImageFileService imageFileService;

    @Autowired
    public TourController(TourService tourService, ImageFileService imageFileService) {
        this.tourService = tourService;
        this.imageFileService = imageFileService;
    }

    @GetMapping("/tours")
    public List<TourDto> getTours() {
        return tourService.getAllTours().stream().map(TourMapper::mapToDto).toList();
    }

    @GetMapping("/tours/{id}")
    public TourDto getTourById(@PathVariable("id") Long id) {
        Tour tour = tourService.getById(id);
        return TourMapper.mapToDto(tour);
    }

    @GetMapping(
        value = "/tours/{id}/map",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getTourMapImage(@PathVariable("id") Long id) {
        Tour tour = tourService.getById(id);
        return imageFileService.readFromFile(tour.getImagePath());
    }

    @PostMapping("/tours")
    public TourDto createTour(@RequestBody TourCreationDto tourDto) {
        //TODO: Add validations

        Tour tour = tourService.createAndPersistTour(tourDto);
        return TourMapper.mapToDto(tour);
    }
}
