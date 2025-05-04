package ai.smartmetal.eaglemap.repository;

import ai.smartmetal.eaglemap.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {}
