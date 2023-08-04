package com.check24.IMDB.api.rate;


import com.check24.IMDB.api.dto.RatingDto;
import com.check24.IMDB.api.dto.mapper.ImdbMapper;
import com.check24.IMDB.model.Film;
import com.check24.IMDB.model.Rating;
import com.check24.IMDB.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RatingRepository ratingRepository;
    private final ImdbMapper imdbMapper;


    public RatingDto registerRate(Film film, UserEntity userEntity, Integer rate) {
        Rating rating = new Rating();
        Optional<Rating> optionalRating = ratingRepository.findByFilmAndUserEntity(film, userEntity);
        if (optionalRating.isPresent()) {
            rating = optionalRating.get();
            rating.setRating(rate);
            ratingRepository.save(rating);
        } else {
            rating.setRating(rate);
            rating.setFilm(film);
            rating.setUserEntity(userEntity);
            ratingRepository.save(rating);
        }
        return imdbMapper.ratingToRatingDto(rating);
    }

}
