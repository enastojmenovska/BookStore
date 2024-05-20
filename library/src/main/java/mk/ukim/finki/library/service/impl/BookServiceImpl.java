package mk.ukim.finki.library.service.impl;


import mk.ukim.finki.library.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.exceptions.BookNotFoundException;
import mk.ukim.finki.library.exceptions.BooksNotAvailableException;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.enumerations.BookCategory;
import mk.ukim.finki.library.model.event.BookCreatedEvent;
import mk.ukim.finki.library.model.event.BookDeletedEvent;
import mk.ukim.finki.library.model.event.BookEditedEvent;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    public Optional<Book> save(String name, BookCategory category, Long authorId, Integer availableCopies) {
        Author author = (Author)this.authorRepository.findById(authorId).orElseThrow(() -> {
            return new AuthorNotFoundException(authorId);
        });
        Book book = new Book(name, category, author, availableCopies);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of((Book)this.bookRepository.save(book));
    }

    public Optional<Book> edit(Long id, String name, BookCategory category, Long authorId, Integer availableCopies) {
        Book book = (Book)this.bookRepository.findById(id).orElseThrow(() -> {
            return new BookNotFoundException(id);
        });
        Author author = (Author)this.authorRepository.findById(authorId).orElseThrow(() -> {
            return new AuthorNotFoundException(authorId);
        });
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        this.applicationEventPublisher.publishEvent(new BookEditedEvent(book));
        return Optional.of(book);
    }

    public void deleteById(Long id) {
        Book book = (Book)this.bookRepository.findById(id).orElseThrow(() -> {
            return new BookNotFoundException(id);
        });
        this.bookRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new BookDeletedEvent(book));
    }

    public Optional<Book> markBookAsRented(Long id) throws BooksNotAvailableException {
        Book book = (Book)this.bookRepository.findById(id).orElseThrow(() -> {
            return new BookNotFoundException(id);
        });
        if (book.getAvailableCopies() == 0) {
            throw new BooksNotAvailableException(id);
        } else {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
            return Optional.of(book);
        }
    }

    public List<BookCategory> getAllBookCategories() {
        return (List) Arrays.stream(BookCategory.values()).collect(Collectors.toList());
    }

    public List<Book> filterBooksByName(String name) {
        return name != null ? this.bookRepository.findByNameContainingIgnoreCase(name) : this.findAll();
    }
}

