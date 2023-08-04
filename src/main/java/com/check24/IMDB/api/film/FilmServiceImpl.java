package com.check24.IMDB.api.film;

import com.check24.IMDB.api.dto.FilmDto;
import com.check24.IMDB.api.dto.RateDto;
import com.check24.IMDB.api.dto.RatingDto;
import com.check24.IMDB.api.dto.mapper.ImdbMapper;
import com.check24.IMDB.api.rate.RateService;
import com.check24.IMDB.api.user.UserService;
import com.check24.IMDB.exception.EntityNotFoundException;
import com.check24.IMDB.model.Film;
import com.check24.IMDB.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final UserService userService;
    private final RateService rateService;
    private final ImdbMapper imdbMapper;


    @Override
    public Page<FilmDto> findFilmsByNameContaining(String name, Pageable pageable) {
        if (name == null || name.isEmpty()) {
            return readAllFilms(pageable);
        } else {
            return readFilmByTitle(name, pageable);
        }
    }

    @Override
    public boolean filmExistsById(Long filmId) {
        return filmRepository.existsById(filmId);
    }

    @Override
    public FilmDto findById(Long filmId) {
        Optional<Film> optionalFilm = filmRepository.findById(filmId);
        if (optionalFilm.isEmpty()) {
            throw new EntityNotFoundException(Film.class, "film_id", String.valueOf(filmId));
        } else {
            FilmDto filmDto = imdbMapper.filmToFilmDto(optionalFilm.get());
            filmDto.setRateAverage(FilmDto.calculateAverageRating(filmDto.getRatings()));
            return filmDto;
        }

    }

    @Override
    public FilmDto rateFilm(Long filmId, RateDto rateDto) {
        if (rateDto.getRate() < 1 || rateDto.getRate() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        Optional<UserEntity> optionalUser = userService.readUserByUsername(rateDto.getUsername());
        Optional<Film> optionalFilm = filmRepository.findById(filmId);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException(UserEntity.class, "username", String.valueOf(rateDto.getUsername()));
        } else if (optionalFilm.isEmpty()) {
            throw new EntityNotFoundException(Film.class, "film_id", String.valueOf(filmId));
        }
        RatingDto ratingDto = rateService.registerRate(optionalFilm.get(), optionalUser.get(), rateDto.getRate());
        FilmDto result = imdbMapper.filmToFilmDto(optionalFilm.get());
        result.getRatings().add(ratingDto);
        result.setRateAverage(FilmDto.calculateAverageRating(result.getRatings()));

        return result;
    }

    private Page<FilmDto> readAllFilms(Pageable pageable) {
        Page<Film> films = filmRepository.findAll(pageable);
        return convertToDto(films);
    }

    private Page<FilmDto> readFilmByTitle(String filmName, Pageable pageable) {
        Page<Film> films = filmRepository.searchByTitle(filmName, pageable);
        return convertToDto(films);
    }

    private Page<FilmDto> convertToDto(Page<Film> films) {
        List<FilmDto> result = new ArrayList<>();
        films.getContent().forEach(film -> {
            FilmDto filmDto = imdbMapper.filmToFilmDto(film);
            filmDto.setRateAverage(FilmDto.calculateAverageRating(filmDto.getRatings()));
            result.add(filmDto);
        });
        Page<FilmDto> page = new PageImpl<>(result);
        return page;
    }

}
