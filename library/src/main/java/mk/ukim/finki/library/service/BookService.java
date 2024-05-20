package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, BookCategory category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, String name, BookCategory category, Long authorId, Integer availableCopies);

    void deleteById(Long id);

    Optional<Book> markBookAsRented(Long id);

    List<BookCategory> getAllBookCategories();

    List<Book> filterBooksByName(String name);
}
