package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.exceptions.CountryNotFoundException;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    public Optional<Country> save(String name, String continent) {
        return Optional.of((Country)this.countryRepository.save(new Country(name, continent)));
    }

    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = (Country)this.countryRepository.findById(id).orElseThrow(() -> {
            return new CountryNotFoundException(id);
        });
        country.setName(name);
        country.setContinent(continent);
        return Optional.of((Country)this.countryRepository.save(country));
    }

    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
