package com.check24.IMDB.api.film;


import com.check24.IMDB.api.dto.FilmDto;
import com.check24.IMDB.api.dto.RateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@WebMvcTest(FilmController.class)
@ExtendWith(MockitoExtension.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Test
    public void testRateFilm() throws Exception {
        Long filmId = 1L;
        RateDto rateDto = new RateDto();
        FilmDto filmDto = new FilmDto();
        when(filmService.rateFilm(filmId, rateDto)).thenReturn(filmDto);

        mockMvc.perform(post("/api/v1/films/{filmId}/rate", filmId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(rateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value(filmDto.getFilmId()));
    }

    @Test
    public void testReadAll() throws Exception {
        String name = "Avengers";
        int page = 0;
        int size = 6;
        Page<FilmDto> filmPage = new PageImpl<>(Collections.singletonList(new FilmDto()));
        when(filmService.findFilmsByNameContaining(eq(name), any(PageRequest.class))).thenReturn(filmPage);

        mockMvc.perform(get("/api/v1/films")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void testReadById() throws Exception {
        Long filmId = 1L;
        FilmDto filmDto = new FilmDto();
        when(filmService.findById(filmId)).thenReturn(filmDto);

        mockMvc.perform(get("/api/v1/films/{filmId}", filmId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value(filmDto.getFilmId()));
    }
}
