package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.movierating.client.config.PosterConfig;
import com.movierating.client.resources.Resources;

import java.util.Date;

public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<HTMLPanel, HomePage> {
    }

    private static final HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);

    interface MyStyle extends CssResource {

        @ClassName("glider-style")
        String gliderStyle();

        @ClassName("releases-header")
        String releasesHeader();

        @ClassName("carousel-home")
        String carouselHome();

        @ClassName("movie-lists")
        String movieLists();

        String first_td();
    }
    @UiField(provided = true)
    PosterMovieCarousels newReleaseMovies;

    @UiField(provided = true)
    PosterMovieCarousels upcomingReleaseMovies;

    @UiField
    MyStyle style;

    @UiField(provided = true)
    MovieTitleList curYearMovieList;

    @UiField(provided = true)
    MovieTitleList prevYearMovieList;

    public HomePage() {
//        injectResources();
        upcomingReleaseMovies = new PosterMovieCarousels(PosterConfig.POSTER_UPCOMING);
        newReleaseMovies = new PosterMovieCarousels(PosterConfig.POSTER_NEW_RELEASES);
        int currentYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
        prevYearMovieList = new MovieTitleList(currentYear - 1);
        curYearMovieList = new MovieTitleList(currentYear);

        initWidget(ourUiBinder.createAndBindUi(this));
    }
}