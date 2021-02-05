package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.controller.MovieService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.styles.MovieHeaderStyle;
import com.movierating.client.resources.styles.MovieScoreStyle;
import com.movierating.client.ui.home.PosterMovieCarousels;
import com.movierating.client.utils.ImageUtils;
import com.movierating.client.utils.ScoreUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class MoviePage extends Composite {
    interface MoviePageUiBinder extends UiBinder<HTMLPanel, MoviePage> {
    }

    private static MoviePageUiBinder ourUiBinder = GWT.create(MoviePageUiBinder.class);

    private static final MovieService movieService = GWT.create(MovieService.class);

    public class SameGenreLazyPanel extends LazyPanel {

        @Override
        protected Widget createWidget() {
            return new PosterMovieCarousels(movie.getGenre());
        }
    }

    private static final String START_YOUTUBE_URL = "https://www.youtube.com/embed/";

    @UiField
    Image coverMedium;

    @UiField
    MovieScoreStyle movieScoreStyle;

    @UiField
    DivElement ratingDiv;

    @UiField
    SpanElement titleElem;

    @UiField
    SpanElement yearElem;

    @UiField
    SpanElement genreElem;

    @UiField
    SpanElement premierDateElem;
    @UiField
    Label descrLabel;

    private Movie movie;

    @UiField(provided = true)
    LazyPanel sameGenrePanel;

    @UiField
    MovieHeaderStyle headerStyles;
    @UiField
    IFrameElement youtubePlayer;

    public MoviePage(Long movieId) {
        sameGenrePanel = new SameGenreLazyPanel();
        sameGenrePanel.setVisible(false);
        initWidget(ourUiBinder.createAndBindUi(this));
        getMovieDetails(movieId);

    }

    private void getMovieDetails(Long movieId) {
        movieService.getMovieDetails(movieId, new MethodCallback<Movie>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                GWT.log(method.getResponse().getText(), exception);
            }

            @Override
            public void onSuccess(Method method, Movie respMovie) {
                movie = respMovie;

                coverMedium.setUrl(ImageUtils.getImageData(movie.getCoverImg()));
                if (movie.getRating() != null) {
                    ScoreUtils.initRatingMarkStyle(movie.getRating(), ratingDiv, movieScoreStyle);
                    ratingDiv.setInnerText(String.valueOf(movie.getRating()));
                } else {
                    ratingDiv.setInnerText("No");
                    ratingDiv.addClassName(movieScoreStyle.neutral());
                }

                titleElem.setInnerText(movie.getTitle());
                yearElem.setInnerText("(" + DateTimeFormat.getFormat("yyyy").format(movie.getPremierDate()) + ")");
                premierDateElem.setInnerText(movie.getPremierDateString());
                genreElem.setInnerText(movie.getGenre());
                descrLabel.setText(movie.getDescription());

                sameGenrePanel.setVisible(true); // start load sameGenreWidget

                initializeVideo(movie.getYoutubeId());
            }
        });
    }

    private void initializeVideo(String youtubeId) {
        youtubePlayer.setAttribute("src", START_YOUTUBE_URL + youtubeId);
    }
}