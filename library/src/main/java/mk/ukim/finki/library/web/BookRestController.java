package mk.ukim.finki.library.web;


import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDto;
import mk.ukim.finki.library.model.enumerations.BookCategory;
import mk.ukim.finki.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping(
        path = {"/api/books", "/api"}
)
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(@RequestParam(required = false) String name) {
        return name == null ? this.bookService.findAll() : this.bookService.filterBooksByName(name);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return (ResponseEntity)this.bookService.findById(id).map((book) -> {
            return ResponseEntity.ok().body(book);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @GetMapping({"/categories"})
    public List<BookCategory> findAllBookCategories() {
        return this.bookService.getAllBookCategories();
    }

    @GetMapping({"/rent/{id}"})
    public ResponseEntity<Book> markAsRented(@PathVariable Long id) {
        return (ResponseEntity)this.bookService.markBookAsRented(id).map((book) -> {
            return ResponseEntity.ok().body(book);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PostMapping({"/add"})
    public ResponseEntity<Book> add(@RequestBody BookDto bookDto) {
        return (ResponseEntity)this.bookService.save(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies()).map((book) -> {
            return ResponseEntity.ok().body(book);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @PutMapping({"/edit/{id}"})
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return (ResponseEntity)this.bookService.edit(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies()).map((book) -> {
            return ResponseEntity.ok().body(book);
        }).orElseGet(() -> {
            return ResponseEntity.badRequest().build();
        });
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent()) {
            this.bookService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
