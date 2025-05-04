package ai.smartmetal.eaglemap.repository;


import ai.smartmetal.eaglemap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {}

