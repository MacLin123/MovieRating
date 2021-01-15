package com.movierating.server.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByTitleIgnoreCase(String title);
    boolean existsByTitleIgnoreCase(String title);
}
