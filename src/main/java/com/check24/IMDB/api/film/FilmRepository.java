package com.check24.IMDB.api.film;

import com.check24.IMDB.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.title LIKE %:title%")
    Page<Film> searchByTitle(String title, Pageable pageable);

    boolean existsById(Long id);
}


