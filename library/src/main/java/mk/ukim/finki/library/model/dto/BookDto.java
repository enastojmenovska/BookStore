package mk.ukim.finki.library.model.dto;

import mk.ukim.finki.library.model.enumerations.BookCategory;

public class BookDto {
    private String name;
    private BookCategory category;
    private Long authorId;
    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, BookCategory category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return this.name;
    }

    public BookCategory getCategory() {
        return this.category;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public Integer getAvailableCopies() {
        return this.availableCopies;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCategory(final BookCategory category) {
        this.category = category;
    }

    public void setAuthorId(final Long authorId) {
        this.authorId = authorId;
    }

    public void setAvailableCopies(final Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

}
