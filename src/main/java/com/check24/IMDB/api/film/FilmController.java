package com.check24.IMDB.api.film;

import com.check24.IMDB.ApiVersion;
import com.check24.IMDB.api.dto.FilmDto;
import com.check24.IMDB.api.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import static com.check24.IMDB.constant.Constant.CONFIGURATION_PAGE_SIZE;


@RestController
@RequestMapping(ApiVersion.VERSION_1 + "/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;


    @PostMapping(value = "/{filmId}/rate")
    public FilmDto rateFilm(@PathVariable Long filmId, @RequestBody @Valid RateDto rateDto) {

        return filmService.rateFilm(filmId, rateDto);
    }

    @GetMapping(value = "/all",params = {"page", "size"})
    public Page<FilmDto> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") @Max(CONFIGURATION_PAGE_SIZE) int size,
            @RequestParam(name = "name", required = false) String name) {

        return filmService.findFilmsByNameContaining(name, PageRequest.of(page, size));
    }

    @GetMapping(value = "/{filmId}")
    public FilmDto readById(@PathVariable Long filmId) {

        return filmService.findById(filmId);
    }
}
