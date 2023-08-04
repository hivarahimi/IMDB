package com.check24.IMDB.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Director implements Serializable {

    private static final String DIRECTOR_GENERATOR = "director_generator";
    @Serial
    private static final long serialVersionUID = 8858597959555757345L;

    @Id
    @GeneratedValue(generator = DIRECTOR_GENERATOR)
    @SequenceGenerator(name = DIRECTOR_GENERATOR, sequenceName = "DIRECTOR_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long directorId;

    private String name;

    @ManyToMany(mappedBy = "directors")
    private Set<Film> films = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
