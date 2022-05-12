package at.technikum.tourplannerbackend.tour;

import at.technikum.tourplannerbackend.dto.TourCreationDto;
import at.technikum.tourplannerbackend.dto.TourDto;
import at.technikum.tourplannerbackend.dto.TourMapper;
import at.technikum.tourplannerbackend.entity.Tour;
import at.technikum.tourplannerbackend.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @GetMapping("/tours")
    public List<TourDto> getTours() {
        return tourRepository.findAll().stream().map(TourMapper::mapToDto).toList();
    }

    @GetMapping("/tours/{id}")
    public TourDto getTourById(@PathVariable Long id) {
        try {
            Tour tour = tourRepository.getById(id);
            return TourMapper.mapToDto(tour);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @PostMapping("/tours")
    public TourDto createTour(@RequestBody TourCreationDto tourDto) {
        Tour tour = tourRepository.save(TourMapper.fromDto(tourDto));
        return TourMapper.mapToDto(tour);
    }
}
