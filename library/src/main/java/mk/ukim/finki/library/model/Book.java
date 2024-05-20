package mk.ukim.finki.library.model;

import jakarta.persistence.*;
import mk.ukim.finki.library.model.enumerations.BookCategory;

@Entity
@Table(
        name = "books"
)
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, BookCategory category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BookCategory getCategory() {
        return this.category;
    }

    public Author getAuthor() {
        return this.author;
    }

    public Integer getAvailableCopies() {
        return this.availableCopies;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCategory(final BookCategory category) {
        this.category = category;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public void setAvailableCopies(final Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
