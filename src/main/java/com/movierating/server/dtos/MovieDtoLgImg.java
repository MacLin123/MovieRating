package com.movierating.server.dtos;

import com.movierating.server.model.Movie;
import com.movierating.server.utils.DateUtils;

import java.util.Date;
import java.util.Objects;

public class MovieDtoLgImg {
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private String genre;

    private byte[] coverImg;

    private Date premierDate;

    private String youtubeId;

    public MovieDtoLgImg(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.genre = movie.getGenre();
        this.premierDate = movie.getPremierDate();
        this.coverImg = movie.getLargeImg();
        this.youtubeId = movie.getYoutubeId();
    }

    public MovieDtoLgImg() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPremierDate() {
        return premierDate;
    }

    public void setPremierDate(Date premierDate) {
        this.premierDate = premierDate;
    }

    public byte[] getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(byte[] coverImg) {
        this.coverImg = coverImg;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @Override
    public String toString() {
        String strDate = DateUtils.dateToString(premierDate);
        return "MovieDtoMdImg{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", premierDate=" + strDate +
                ", youtubeId= " + youtubeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDtoLgImg movie = (MovieDtoLgImg) o;
        return id.equals(movie.id) && title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
