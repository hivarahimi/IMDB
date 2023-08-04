package com.check24.IMDB.api.rate;

import com.check24.IMDB.api.dto.RatingDto;
import com.check24.IMDB.model.Film;
import com.check24.IMDB.model.UserEntity;

public interface RateService {

    RatingDto registerRate(Film film, UserEntity userEntity, Integer rate);
}
