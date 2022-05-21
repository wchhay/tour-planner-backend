package at.technikum.tourplannerbackend.dal.repository;

import at.technikum.tourplannerbackend.dal.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
