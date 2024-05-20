package mk.ukim.finki.library.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("Book with id: %d is not found", id));
    }
}
