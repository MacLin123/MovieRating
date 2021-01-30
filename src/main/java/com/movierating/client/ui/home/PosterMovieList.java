package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.Pages;
import com.movierating.client.config.PosterConfig;
import com.movierating.client.controller.HomeService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.resources.styles.MovieHeaderStyle;
import com.movierating.client.ui.movie.MovieFormPanel;
import com.movierating.client.ui.movie.MoviePage;
import com.movierating.client.utils.ImageUtils;
import com.movierating.client.widgets.GliderWrapper;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class PosterMovieList extends Composite {
    interface PosterMovieListUiBinder extends UiBinder<HTMLPanel, PosterMovieList> {
    }

    private static PosterMovieListUiBinder ourUiBinder = GWT.create(PosterMovieListUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);
    private static final HomeService homeService = GWT.create(HomeService.class);

    interface MyStyle extends CssResource {

        @ClassName("glider-style")
        String gliderStyle();

        @ClassName("poster-panel")
        String posterPanel();

        @ClassName("home-panel")
        String homePanel();
    }


    @UiField
    Element header;

    @UiField
    Button viewAllBtn;

    @UiField
    MyStyle style;

    @UiField
    MovieHeaderStyle headerStyles;

    GliderWrapper gliderWrapper;

    @UiField
    Element newReleasesDiv;

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

    public PosterMovieList(PosterConfig typeOfPoster) {
        initWidget(ourUiBinder.createAndBindUi(this));
        if (typeOfPoster.equals(PosterConfig.POSTER_NEW_RELEASES)) {
            getNewReleases();
            header.setInnerText("New Releases");
        } else if (typeOfPoster.equals(PosterConfig.POSTER_UPCOMING)) {
            getUpcomingReleases();
            header.setInnerText("Upcoming Releases");
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
            initGliderWrapper();
//            GWT.debugger();
            for (Movie movie : movies) {
                Image image = new Image(ImageUtils.getImageData(movie.getCoverImg()));
//                image.addClickHandler(event -> {
//                    Window.alert("fsdfsf");
//                    History.newItem(Pages.DETAILS_MOVIE.getStrValue());
//                    RootPanel.get("content").clear();
////                    RootPanel.get("content").add(new MoviePage());
//                });
                MoviePoster moviePoster = new MoviePoster(image, movie.getTitle(), movie.getRating());
                gliderWrapper.addItem(moviePoster.getElement());
            }
            //remove layering styles( js added these classes every timi
            gliderWrapper.getGliderElem().removeClassName("glider");
            gliderWrapper.getGliderElem().removeClassName("glider-contain");


        }
    }


    private void initGliderWrapper() {
        gliderWrapper = new GliderWrapper();
        gliderWrapper.createGlider(newReleasesDiv);
        gliderWrapper.getGliderElem().addClassName(style.gliderStyle());
    }
}