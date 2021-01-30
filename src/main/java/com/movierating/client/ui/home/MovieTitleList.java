package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.controller.HomeService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.styles.MovieHeaderStyle;
import com.movierating.client.resources.styles.MovieScoreStyle;
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
                    bestMoviesTable.setText(row, 0, movie.getTitle());

                    Integer ratingVal = movie.getRating();
                    String ratingStr = (ratingVal != null) ?
                            String.valueOf(ratingVal) : "";

                    Label rating = new Label(ratingStr);

                    bestMoviesTable.setWidget(row, 1, rating);

                    initCellStyle(row);
                    initRatingStyle(ratingVal, rating);
                }
            }
        });
    }

    private void initRatingStyle(Integer rating, Label ratingLabel) {
        ratingLabel.addStyleName(movieScoreStyle.small());
        ratingLabel.addStyleName(movieScoreStyle.moviescore_w());

        if (rating == null) return;
        if (rating >= 70) {
            ratingLabel.addStyleName(movieScoreStyle.positive());
        } else if (rating < 50) {
            ratingLabel.addStyleName(movieScoreStyle.negative());
        } else {
            ratingLabel.addStyleName(movieScoreStyle.mixed());
        }
    }

    private void initCellStyle(int row) {
        bestMoviesTable.getCellFormatter().addStyleName(row, 0, style.bestMoviesListCellTitle());
        bestMoviesTable.getCellFormatter().addStyleName(row, 1, style.bestMoviesListCellGlobal());

        for (int i = 0; i < COL_AMOUNT; i++) {
            bestMoviesTable.getCellFormatter().addStyleName(row, i, style.bestMoviesListRow());
        }
    }
}