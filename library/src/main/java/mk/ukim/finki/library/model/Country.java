package mk.ukim.finki.library.model;


import jakarta.persistence.*;

@Entity
@Table(
        name = "countries"
)
public class Country {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String continent;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setContinent(final String continent) {
        this.continent = continent;
    }
}
