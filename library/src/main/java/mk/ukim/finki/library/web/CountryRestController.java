package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/api/countries"})
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return (ResponseEntity)this.countryService.findById(id).map((country) -> {
            return ResponseEntity.ok().body(country);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PostMapping({"/add"})
    public ResponseEntity<Country> add(@RequestParam String name, @RequestParam String continent) {
        return (ResponseEntity)this.countryService.save(name, continent).map((country) -> {
            return ResponseEntity.ok().body(country);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PostMapping({"/edit/{id}"})
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestParam String name, @RequestParam String continent) {
        return (ResponseEntity)this.countryService.edit(id, name, continent).map((country) -> {
            return ResponseEntity.ok().body(country);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (this.countryService.findById(id).isPresent()) {
            this.countryService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

