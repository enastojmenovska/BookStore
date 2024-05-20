package mk.ukim.finki.library.service.impl;


import mk.ukim.finki.library.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.exceptions.CountryNotFoundException;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = (Country)this.countryRepository.findById(countryId).orElseThrow(() -> {
            return new CountryNotFoundException(countryId);
        });
        return Optional.of((Author)this.authorRepository.save(new Author(name, surname, country)));
    }

    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = (Author)this.authorRepository.findById(id).orElseThrow(() -> {
            return new AuthorNotFoundException(id);
        });
        Country country = (Country)this.countryRepository.findById(countryId).orElseThrow(() -> {
            return new CountryNotFoundException(countryId);
        });
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

