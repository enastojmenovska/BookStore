package mk.ukim.finki.library.exceptions;

public class BooksNotAvailableException extends RuntimeException {
    public BooksNotAvailableException(Long id) {
        super(String.format("Book with id: %d is not available", id));
    }
}