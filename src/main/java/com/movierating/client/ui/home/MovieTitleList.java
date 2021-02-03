package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.Pages;
import com.movierating.client.controller.HomeService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.styles.MovieHeaderStyle;
import com.movierating.client.resources.styles.MovieScoreStyle;
import com.movierating.client.ui.movie.MoviePage;
import com.movierating.client.utils.ScoreUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class MovieTitleList extends Composite {
    interface MovieTitleListUiBinder extends UiBinder<HTMLPanel, MovieTitleList> {
    }

    private static MovieTitleListUiBinder ourUiBinder = GWT.create(MovieTitleListUiBinder.class);

//    @UiField
//    VerticalPanel bestMoviesPanel;

    interface MyStyle extends CssResource {

        @ClassName("best-movies-list")
        String bestMoviesList();

        @ClassName("best-movies-list-cell-title")
        String bestMoviesListCellTitle();

        @ClassName("best-movies-list-cell-global")
        String bestMoviesListCellGlobal();

        @ClassName("best-movies-list-row")
        String bestMoviesListRow();

        @ClassName("div-header")
        String divHeader();

        @ClassName("rating-label")
        String ratingLabel();
    }

    @UiField
    FlexTable bestMoviesTable;

    @UiField
    Button viewAllBtn;

    @UiField
    HeadingElement bestMoviesHeader;

    @UiField
    MyStyle style;

    @UiField
    MovieScoreStyle movieScoreStyle;

    @UiField
    MovieHeaderStyle headerStyles;

    private static final int COL_AMOUNT = 2;

    HomeService homeService = GWT.create(HomeService.class);

    public MovieTitleList(int year) {
        initWidget(ourUiBinder.createAndBindUi(this));
//        int currentYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
        bestMoviesHeader.setInnerText("Best Movies " + year);
        getBestMovies(year);

    }

    public void getBestMovies(int year) {
        homeService.getBestMoviesByYear(year, new MethodCallback<List<Movie>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                GWT.log(method.getResponse().getText(), exception);
            }

            @Override
            public void onSuccess(Method method, List<Movie> movies) {
                bestMoviesTable.clear();
                for (Movie movie : movies) {
                    int row = bestMoviesTable.getRowCount();
                    //title
                    Label title = new Label(movie.getTitle());
                    bestMoviesTable.setWidget(row, 0, title);

                    title.addClickHandler(event -> {
                        History.newItem(Pages.DETAILS_MOVIE.getStrValue());
                        RootPanel.get("content").clear();
                        RootPanel.get("content").add(new MoviePage(movie.getId()));
                    });

                    //rating
                    Integer ratingVal = movie.getRating();
                    String ratingStr = (ratingVal != null) ?
                            String.valueOf(ratingVal) : "";

                    Label rating = new Label(ratingStr);

                    bestMoviesTable.setWidget(row, 1, rating);

                    initCellStyle(row);
                    initRatingStyle(ratingVal, rating);
                    title.addStyleName(style.ratingLabel());
                }
            }
        });
    }

    private void initRatingStyle(Integer rating, Label ratingLabel) {
        ratingLabel.addStyleName(movieScoreStyle.small());
        ratingLabel.addStyleName(movieScoreStyle.moviescore_w());

        ScoreUtils.initRatingMarkStyle(rating, ratingLabel, movieScoreStyle);
    }

    private void initCellStyle(int row) {
        bestMoviesTable.getCellFormatter().addStyleName(row, 0, style.bestMoviesListCellTitle());
//        bestMoviesTable.getCellFormatter().addStyleName(row, 0, style.ratingLabel());
        bestMoviesTable.getCellFormatter().addStyleName(row, 1, style.bestMoviesListCellGlobal());

        for (int i = 0; i < COL_AMOUNT; i++) {
            bestMoviesTable.getCellFormatter().addStyleName(row, i, style.bestMoviesListRow());
        }
    }
}