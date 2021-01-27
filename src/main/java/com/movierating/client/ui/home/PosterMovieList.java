package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.PosterConfig;
import com.movierating.client.controller.HomeService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.utils.ImageUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class PosterMovieList extends Composite {
    interface PosterMovieListUiBinder extends UiBinder<HTMLPanel, PosterMovieList> {
    }

    private static PosterMovieListUiBinder ourUiBinder = GWT.create(PosterMovieListUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);
    private static final HomeService homeService = GWT.create(HomeService.class);


    @UiField
    FlowPanel posterPanel;

    @UiField
    Button viewAllBtn;

    Object glider;


    public PosterMovieList() {
        initWidget(ourUiBinder.createAndBindUi(this));
//        getNewReleasesMovie();
//        posterPanel.add(new MoviePoster(new Image(resources.emptyCover()), "title", 52));
//        posterPanel.add(new MoviePoster(new Image(resources.emptyCover()), "title", 52));
//        posterPanel.add(new MoviePoster(new Image(resources.emptyCover()), "title", 52));
//        posterPanel.add(new MoviePoster(new Image(resources.emptyCover()), "title", 52));
//        posterPanel.add(new MoviePoster(new Image(resources.emptyCover()), "title", 52));
//        posterPanel.setSpacing(100);
    }

    public PosterMovieList(PosterConfig typeOfPoster, Object glider) {
        this.glider = glider;
        initWidget(ourUiBinder.createAndBindUi(this));
        if (typeOfPoster.equals(PosterConfig.POSTER_NEW_RELEASES)) {
            getNewReleases();
        } else if (typeOfPoster.equals(PosterConfig.POSTER_UPCOMING)) {
            getUpcomingReleases();
        }
    }

    private void getUpcomingReleases() {
        homeService.getUpcomingReleases(new MoviePosterCallback());
    }

    private void getNewReleases() {
        homeService.getNewReleases(new MoviePosterCallback());
    }

    public class MoviePosterCallback implements MethodCallback<List<Movie>> {
        @Override
        public void onFailure(Method method, Throwable exception) {
            GWT.log(method.getResponse().getText(), exception);
        }

        @Override
        public void onSuccess(Method method, List<Movie> movies) {
            posterPanel.clear();
            for (Movie movie : movies) {
                MoviePoster moviePoster = new MoviePoster(new Image(ImageUtils.getImageData(movie.getCoverImg())),
                        movie.getTitle(), movie.getRating());
//                posterPanel.add(moviePoster);
//                addItem(glider, new Label("123").getElement());
                addItem(glider,moviePoster.getElement());
            }
        }
    }
    private native void addItem(Object glider, Element itemToAdd) /*-{
        glider.addItem(itemToAdd);
    }-*/;
}