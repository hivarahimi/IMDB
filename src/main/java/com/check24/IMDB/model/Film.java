package com.check24.IMDB.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"filmId", "genres", "directors", "ratings"})
public class Film implements Serializable {

    @Serial
    private static final long serialVersionUID = 6802868364278468996L;

    private static final String FILM_GENERATOR = "film_generator";

    @Id
    @GeneratedValue(generator = FILM_GENERATOR)
    @SequenceGenerator(name = FILM_GENERATOR, sequenceName = "FILM_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long filmId;
    private String title;
    private String description;
    private Integer releaseYear;


    @ManyToMany
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "film_director",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<>();


}
