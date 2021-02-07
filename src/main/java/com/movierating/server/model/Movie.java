package com.movierating.server.model;

import com.movierating.server.config.ConfigMovie;
import com.movierating.server.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer rating;
    private String genre;

    @Column(name = "cover_img")
    private byte[] coverImg;

    @Column(name = "medium_img")
    private byte[] mediumImg;

    @Column(name = "large_img")
    private byte[] largeImg;

    @Column(name = "premier_date")
    private Date premierDate;

    @Column(name="youtube_id")
    private String youtubeId;

    public Movie(String title, String description, String genre, Date premierDate,String youtubeId) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.premierDate = premierDate;
        initMovieRating();
        this.youtubeId = youtubeId;
    }

    public Movie(String title, String description, String genre, Date premierDate,
                 String youtubeId, HashMap<String, byte[]> imgMap) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.premierDate = premierDate;
        this.coverImg = imgMap.get(ConfigMovie.IMG_COVER_KEY.getText());
        this.mediumImg = imgMap.get(ConfigMovie.IMG_MEDIUM_KEY.getText());
        this.largeImg = imgMap.get(ConfigMovie.IMG_LARGE_KEY.getText());
        initMovieRating();
        this.youtubeId = youtubeId;
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

    public void setPremierDate(Date premierDate) {
        this.premierDate = premierDate;
    }

    public byte[] getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(byte[] img) {
        this.coverImg = img;
    }

    public byte[] getMediumImg() {
        return mediumImg;
    }

    public void setMediumImg(byte[] mediumImg) {
        this.mediumImg = mediumImg;
    }

    public byte[] getLargeImg() {
        return largeImg;
    }

    public void setLargeImg(byte[] largeImg) {
        this.largeImg = largeImg;
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
        return "Movie{" +
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
        Movie movie = (Movie) o;
        return id.equals(movie.id) && title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    private void initMovieRating() {
        if (premierDate.before(new Date())) {
            this.rating = new Random().nextInt(ConfigMovie.MAX_RATING.getValue() + 1);
        }
    }

}
