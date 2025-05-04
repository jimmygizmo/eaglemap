package ai.smartmetal.eaglemap.controller;

import ai.smartmetal.eaglemap.model.Location;
import ai.smartmetal.eaglemap.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> getAll() {
        return service.getAllLocations();
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return service.saveLocation(location);
    }
}
