package com.movierating.server.repository;

import com.movierating.server.model.Movie;
import com.movierating.server.views.MovieViewSmImg;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //        List<Movie> findByTitleContainingIgnoreCase(String title);
    <T> List<T> findByTitleContainingIgnoreCase(String title, Class<T> type);

    <T> T getById(Long id, Class<T> type);

//    List<Movie> findByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCase(String title);

    <T> List<T> findFirst10ByPremierDateBetween(Date start, Date end, Class<T> type);

    <T> List<T> findFirst10ByGenreContainingIgnoreCase(String genre, Class<T> type);

    <T> List<T> findFirst10ByPremierDateAfter(Date start, Class<T> type);

    <T> List<T> findFirst10ByPremierDateBetweenOrderByRatingDesc(Date start, Date end, Class<T> type);

    @Query("SELECT new com.movierating.server.views.MovieViewSmImg(m.id,m.title,m.rating) " +
            "FROM Movie m " +
            "WHERE m.premierDate BETWEEN :start AND :end " +
            "ORDER BY m.rating DESC NULLS LAST ")
    List<MovieViewSmImg> findFirst10BestMovies(Date start, Date end, Pageable pageable);
}
