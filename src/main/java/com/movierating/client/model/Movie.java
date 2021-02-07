package com.movierating.client.model;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class Movie {
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private String genre;
    private byte[] coverImg;
    private Date premierDate;
    private String youtubeId;

    public Movie() {

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

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getPremierDateString() {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
        return dateTimeFormat.format(premierDate);
    }

    public void setPremierDate(Date premierDate) {
        this.premierDate = premierDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", premierDate=" + getPremierDateString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) && title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    public byte[] getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(byte[] coverImg) {
        this.coverImg = coverImg;
    }
}

