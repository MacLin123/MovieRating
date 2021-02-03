package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.movierating.client.config.PosterConfig;
import com.movierating.client.controller.HomeService;
import com.movierating.client.controller.MovieService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.resources.styles.MovieHeaderStyle;
import com.movierating.client.utils.ImageUtils;
import com.movierating.client.widgets.GliderWrapper;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;
import java.util.Locale;

public class PosterMovieList extends Composite {
    interface PosterMovieListUiBinder extends UiBinder<HTMLPanel, PosterMovieList> {
    }

    private static PosterMovieListUiBinder ourUiBinder = GWT.create(PosterMovieListUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);
    private static final HomeService homeService = GWT.create(HomeService.class);
    private static final MovieService movieService = GWT.create(MovieService.class);

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
    }

    public PosterMovieList(PosterConfig typeOfPoster) {
        injectResources();
        initWidget(ourUiBinder.createAndBindUi(this));
        if (typeOfPoster.equals(PosterConfig.POSTER_NEW_RELEASES)) {
            getNewReleases();
            header.setInnerText("New Releases");
        } else if (typeOfPoster.equals(PosterConfig.POSTER_UPCOMING)) {
            getUpcomingReleases();
            header.setInnerText("Upcoming Releases");
        }
    }

    public PosterMovieList(String genre) {
        injectResources();
        initWidget(ourUiBinder.createAndBindUi(this));

        getSameGenreMovies(genre);
        header.setInnerText("You might also like".toUpperCase());
        viewAllBtn.setVisible(false);
    }


    private void getUpcomingReleases() {
        homeService.getUpcomingReleases(new MoviePosterCallback());
    }

    private void getNewReleases() {
        homeService.getNewReleases(new MoviePosterCallback());
    }

    private void getSameGenreMovies(String genre) {
        GWT.log(genre);
        movieService.getMoviesByGenre(genre, new MoviePosterCallback());
    }

    public class MoviePosterCallback implements MethodCallback<List<Movie>> {
        @Override
        public void onFailure(Method method, Throwable exception) {
            GWT.log(method.getResponse().getText(), exception);
        }

        @Override
        public void onSuccess(Method method, List<Movie> movies) {
            initGliderWrapper();
            for (Movie movie : movies) {
                Image image = new Image(ImageUtils.getImageData(movie.getCoverImg()));
//                image.addClickHandler(event -> {
//                    Window.alert("fsdfsf");
//                    History.newItem(Pages.DETAILS_MOVIE.getStrValue());
//                    RootPanel.get("content").clear();
////                    RootPanel.get("content").add(new MoviePage());
//                });
                MoviePoster moviePoster = new MoviePoster(image, movie.getTitle(), movie.getRating());
                gliderWrapper.addItem(moviePoster.getElement(), movie.getId());
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

    private void injectResources() {
        ScriptInjector.fromString(Resources.INSTANCE.gliderJs().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .inject();
        StyleInjector.inject(Resources.INSTANCE.gliderCss().getText());
    }
}