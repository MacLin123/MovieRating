package com.movierating.server.repository;

import com.movierating.server.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
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

    <T> List<T> findFirst10ByPremierDateAfter(Date start, Class<T> type);


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
