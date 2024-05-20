package mk.ukim.finki.library.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id: %d is not found", id));
    }
}
