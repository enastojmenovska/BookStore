package mk.ukim.finki.library.model.dto;

public class AuthorDto {
    private String name;
    private String surname;
    private Long countryId;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public void setCountryId(final Long countryId) {
        this.countryId = countryId;
    }

}
