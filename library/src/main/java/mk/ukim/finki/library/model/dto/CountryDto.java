package mk.ukim.finki.library.model.dto;

public class CountryDto {
    String name;
    String continent;

    public CountryDto() {
    }

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return this.name;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setContinent(final String continent) {
        this.continent = continent;
    }

}
