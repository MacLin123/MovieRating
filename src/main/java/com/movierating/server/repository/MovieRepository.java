package com.movierating.server.repository;

import com.movierating.server.model.Movie;
import com.movierating.server.views.MovieDtoSmImg;
import com.movierating.server.views.MovieViewSmImgDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
//        List<Movie> findByTitleContainingIgnoreCase(String title);
    <T> List<T> findByTitleContainingIgnoreCase(String title);
    <T> T getById(Long id, Class<T> type);

    List<Movie> findByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCase(String title);

//    @Query(nativeQuery = true, value = "" +
//            "SELECT title,description,rating,genre,premier_date,:img_type " +
//            "FROM movies " +
//            "WHERE title LIKE '%' || :title_to_find || '%'")
//    List<MovieDtoSmImg> getMovieDtoSmImg(@Param("title_to_find") String title, @Param("img_type") String imgType);
//
//    @Query(nativeQuery = true, value = "" +
//            "SELECT title,description,rating,genre,premier_date,:img_type " +
//            "FROM movies " +
//            "WHERE title LIKE '%' || :title_to_find || '%'")
//        //    @Query(nativeQuery = true, value = "SELECT * FROM vReport1_1 ORDER BY DATE_CREATED, AMOUNT")
//    List<MovieViewSmImgDto> getMovieView(@Param("title_to_find") String title, @Param("img_type") String imgType);
}
