package ai.smartmetal.eaglemap.service;

import ai.smartmetal.eaglemap.model.Location;
import ai.smartmetal.eaglemap.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    public Optional<Location> getLocation(Long id) {
        return repository.findById(id);
    }

    public Location saveLocation(Location location) {
        return repository.save(location);
    }
}
