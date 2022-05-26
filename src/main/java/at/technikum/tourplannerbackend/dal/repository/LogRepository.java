package at.technikum.tourplannerbackend.dal.repository;

import at.technikum.tourplannerbackend.dal.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, UUID> {
}
