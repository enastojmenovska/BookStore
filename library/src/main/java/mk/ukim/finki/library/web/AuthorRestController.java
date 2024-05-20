package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/api/authors"})
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return (ResponseEntity)this.authorService.findById(id).map((author) -> {
            return ResponseEntity.ok().body(author);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PostMapping({"/add"})
    public ResponseEntity<Author> add(@RequestParam String name, @RequestParam String surname, @RequestParam Long countryId) {
        return (ResponseEntity)this.authorService.save(name, surname, countryId).map((author) -> {
            return ResponseEntity.ok().body(author);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PostMapping({"/edit/{id}"})
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestParam String name, @RequestParam String surname, @RequestParam Long countryId) {
        return (ResponseEntity)this.authorService.edit(id, name, surname, countryId).map((author) -> {
            return ResponseEntity.ok().body(author);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (this.authorService.findById(id).isPresent()) {
            this.authorService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

