package com.check24.IMDB.api.film;

import com.check24.IMDB.api.dto.FilmDto;
import com.check24.IMDB.api.dto.RateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {

    Page<FilmDto> findFilmsByNameContaining(String name, Pageable pageable);

    boolean filmExistsById(Long filmId);

    FilmDto findById(Long filmId);

    FilmDto rateFilm(Long filmId, RateDto rateDto);

}
