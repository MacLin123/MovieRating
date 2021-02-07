package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.movierating.client.config.PosterConfig;

import java.util.Date;

public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<HTMLPanel, HomePage> {
    }

    private static final HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);

    @UiField(provided = true)
    PosterMovieCarousels newReleaseMovies;

    @UiField(provided = true)
    PosterMovieCarousels upcomingReleaseMovies;


    @UiField(provided = true)
    MovieTitleList curYearMovieList;

    @UiField(provided = true)
    MovieTitleList prevYearMovieList;

    public HomePage() {
        upcomingReleaseMovies = new PosterMovieCarousels(PosterConfig.POSTER_UPCOMING);
        newReleaseMovies = new PosterMovieCarousels(PosterConfig.POSTER_NEW_RELEASES);
        int currentYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
        prevYearMovieList = new MovieTitleList(currentYear - 1);
        curYearMovieList = new MovieTitleList(currentYear);

        initWidget(ourUiBinder.createAndBindUi(this));
    }
}