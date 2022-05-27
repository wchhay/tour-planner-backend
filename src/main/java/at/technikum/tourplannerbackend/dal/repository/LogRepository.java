package at.technikum.tourplannerbackend.dal.repository;

import at.technikum.tourplannerbackend.dal.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
