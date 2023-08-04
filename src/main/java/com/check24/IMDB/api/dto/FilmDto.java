package com.check24.IMDB.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class FilmDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 500430564122245391L;

    private Long filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Double rateAverage;
    private Set<DirectorDto> directors= new LinkedHashSet<>();
    private Set<GenreDto> genres;
    private Set<RatingDto> ratings;

    public static double calculateAverageRating(Set<RatingDto> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }

        return ratings.stream().mapToInt(RatingDto::getRating).average().orElse(0.0);
    }
}
