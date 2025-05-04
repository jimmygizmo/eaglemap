package ai.smartmetal.eaglemap.repository;

import ai.smartmetal.eaglemap.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {}
