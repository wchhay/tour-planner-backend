package at.technikum.tourplannerbackend.dal.repository;

import at.technikum.tourplannerbackend.dal.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TourRepository extends JpaRepository<Tour, UUID> {
}
