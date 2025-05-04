package ai.smartmetal.eaglemap.repository;

import ai.smartmetal.eaglemap.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {}
