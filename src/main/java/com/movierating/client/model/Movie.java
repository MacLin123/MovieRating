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

    private Date premierDate;

    public Movie(Long id, String title, String description, String genre, Date premierDate) {
        this.id = id;
        this.title = title;
        this.description = description;
//        this.rating = rating;
        this.genre = genre;
        this.premierDate = premierDate;
    }

    public Movie(String title, String description, String genre, Date premierDate) {
        this.title = title;
        this.description = description;
//        this.rating = rating;
        this.genre = genre;
        this.premierDate = premierDate;
    }

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

    public String getPremierDateString() {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
        String dateStr = dateTimeFormat.format(premierDate);
        return dateStr;
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
}

