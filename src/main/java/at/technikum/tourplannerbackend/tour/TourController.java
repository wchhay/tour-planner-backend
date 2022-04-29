package at.technikum.tourplannerbackend.tour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TourController {

    @GetMapping("/tours")
    public List<String> getTours() {
        return List.of("Tour 1", "Tour 2", "Tour 3", "Tour 4");
    }

    @GetMapping("/tours/{id}")
    public Tour getTourById(@PathVariable Integer id) {
        Tour tour = new Tour();
        tour.setId(UUID.randomUUID());
        tour.setName("Tour 1");
        return tour;
    }
}
