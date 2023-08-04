package com.check24.IMDB.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Rating implements Serializable {
    private static final String RATING_GENERATOR = "rating_generator";
    @Serial
    private static final long serialVersionUID = 5332743522227776648L;


    @Id
    @GeneratedValue(generator = RATING_GENERATOR)
    @SequenceGenerator(name = RATING_GENERATOR, sequenceName = "RATING_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long ratingId;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(ratingId, rating1.ratingId) && Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, rating);
    }
}
