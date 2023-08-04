package com.check24.IMDB.api.rate;

import com.check24.IMDB.model.Film;
import com.check24.IMDB.model.Rating;
import com.check24.IMDB.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
interface RatingRepository extends JpaRepository<Rating, Long> {



    @Query(value = "SELECT * FROM rating r WHERE r.film_id = ?1 AND r.user_id = ?2", nativeQuery = true)
    Optional<Rating> findRatingsByFilmAndUser(Long filmId, Long userId);


    Optional<Rating> findByFilmAndUserEntity(Film film, UserEntity userEntity);
}
