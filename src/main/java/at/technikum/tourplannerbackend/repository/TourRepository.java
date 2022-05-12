package at.technikum.tourplannerbackend.repository;

import at.technikum.tourplannerbackend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
