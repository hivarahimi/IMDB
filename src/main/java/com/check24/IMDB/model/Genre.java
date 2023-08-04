package com.check24.IMDB.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Genre  implements Serializable {

    private static final String GENRE_GENERATOR = "genre_generator";
    @Serial
    private static final long serialVersionUID = 1975734980452889426L;
    @Id
    @GeneratedValue(generator = GENRE_GENERATOR)
    @SequenceGenerator(name = GENRE_GENERATOR, sequenceName = "GENRE_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long genreId;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Film> films = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
