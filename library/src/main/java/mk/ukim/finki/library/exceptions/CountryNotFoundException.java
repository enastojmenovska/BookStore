package mk.ukim.finki.library.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d is not found", id));
    }
}
