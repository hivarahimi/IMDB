package com.check24.IMDB.api.dto.mapper;

import com.check24.IMDB.api.dto.FilmDto;
import com.check24.IMDB.api.dto.RatingDto;
import com.check24.IMDB.api.dto.UserDto;
import com.check24.IMDB.api.film.FilmService;
import com.check24.IMDB.api.rate.RateService;
import com.check24.IMDB.model.Film;
import com.check24.IMDB.model.Rating;
import com.check24.IMDB.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;


@Mapper(componentModel="spring", uses= {FilmService.class, RateService.class})
public interface ImdbMapper {

    //region DTO TO ENTITY
    Film filmDtoToFilm(FilmDto filmDto);
    @Mapping(target = "userEntity",source = "userDto")
    Rating ratingDtoToRating(RatingDto ratingDto);
    Set<Rating> ratingDtoSetToRatingSet(Set<RatingDto> set);
    UserEntity userDtoToUserEntity(UserDto userDto);

    //endregion

    //region ENTITY TO DTO

    FilmDto filmToFilmDto(Film film);
    Set<RatingDto> ratingSetToRatingDtoSet(Set<Rating> ratingSet);
    @Mapping( target = "userDto", source = "userEntity")
    RatingDto ratingToRatingDto(Rating rating);
    UserDto userEntityToUserDto(UserEntity userEntity);

    //endregion


}
