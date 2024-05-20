package mk.ukim.finki.library.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.enumerations.BookCategory;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Country> countries = null;
    public static List<Author> authors = null;
    public static List<Book> books = null;
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        countries = new ArrayList();
        authors = new ArrayList();
        books = new ArrayList();
        if (this.countryRepository.count() == 0L) {
            countries.add(new Country("Italy", "EU"));
            countries.add(new Country("UK", "EU"));
            this.countryRepository.saveAll(countries);
        }

        List authorList;
        if (this.authorRepository.count() == 0L) {
            authorList = this.countryRepository.findAll();
            authors.add(new Author("William", "Shakespeare", (Country)authorList.get(1)));
            authors.add(new Author("Dante", "Alighieri", (Country)authorList.get(0)));
            this.authorRepository.saveAll(authors);
        }

        if (this.bookRepository.count() == 0L) {
            authorList = this.authorRepository.findAll();
            books.add(new Book("Inferno", BookCategory.CLASSICS, (Author)authorList.get(1), 100));
            books.add(new Book("Romeo and Juliet", BookCategory.CLASSICS, (Author)authorList.get(0), 70));
            books.add(new Book("Book3", BookCategory.CLASSICS, (Author)authorList.get(1), 70));
            books.add(new Book("Book4", BookCategory.CLASSICS, (Author)authorList.get(1), 170));
            books.add(new Book("Book5", BookCategory.CLASSICS, (Author)authorList.get(0), 270));
            books.add(new Book("Book6", BookCategory.CLASSICS, (Author)authorList.get(1), 470));
            books.add(new Book("Book7", BookCategory.CLASSICS, (Author)authorList.get(0), 870));
            this.bookRepository.saveAll(books);
        }

    }
}
