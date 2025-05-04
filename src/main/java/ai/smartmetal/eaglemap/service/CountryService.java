package ai.smartmetal.eaglemap.service;

import ai.smartmetal.eaglemap.model.Country;
import ai.smartmetal.eaglemap.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository repository) {
        this.countryRepository = repository;
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountry(Long id) {
        return countryRepository.findById(id);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
}
