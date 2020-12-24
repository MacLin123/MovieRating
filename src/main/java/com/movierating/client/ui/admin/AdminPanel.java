package com.movierating.client.ui.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.controller.AdminService;
import com.movierating.client.model.Movie;
import com.movierating.client.ui.movie.MovieLabel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AdminPanel extends Composite {
    interface AdminPanelUiBinder extends UiBinder<HTMLPanel, AdminPanel> {
    }

    private static AdminPanelUiBinder ourUiBinder = GWT.create(AdminPanelUiBinder.class);

    private static final AdminService adminService = GWT.create(AdminService.class);

    private static final int AMOUNT_VIEW_MOVIES = 10;

    @UiField
    FlowPanel movieList;

    @UiField
    TextBox movieTitleTextBox;

    @UiField
    TextArea movieDescrTextArea;

//    @UiField
//    TextBox movieRatingTextBox;

//    @UiField
//    TextBox movieGanreTextBox;

    @UiField
    Button addMovieBtn;


    public AdminPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));

        refreshMovies();

        addMovieBtn.addClickHandler(event -> {
            final String title = movieTitleTextBox.getText();
            final String description = movieDescrTextArea.getText();
            if (!(title.isEmpty() && description.isEmpty())) {
                addMovie(new Movie(title, description, 2, "4"));
            }
        });

        movieTitleTextBox.getElement().setAttribute("placeholder", "title");
        movieDescrTextArea.getElement().setAttribute("placeholder", "description");

//        movieTitleTextBox.addKeyUpHandler(event -> {
//            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//                final String title = movieTitleTextBox.getText();
//                final String description = movieDescrTextArea.getText();
//                if (!(title.isEmpty() && description.isEmpty())) {
//                    addMovie(new Movie(title, description, 2, "4"));
//                }
//            }
//        });

    }

    /**
     * get data from the server and refresh front-end
     */
    private void refreshMovies() {
        adminService.getRecentMovies(String.valueOf(AMOUNT_VIEW_MOVIES), new MethodCallback<List<Movie>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, List<Movie> response) {
                movieList.clear();
                for (final Movie movie : response) {
                    final MovieLabel movieLabel = new MovieLabel(movie);
                    movieLabel.addClickHandler(movieToRemove -> removeMovie(movieToRemove));
                    movieList.add(movieLabel);
                }
            }

            /**
             * Remove movie from the server
             * @param movieToRemove
             */

            private void removeMovie(final Movie movieToRemove) {
                adminService.deleteMovie(movieToRemove, new MethodCallback<Void>() {
                    @Override
                    public void onFailure(final Method method, final Throwable exception) {

                    }

                    @Override
                    public void onSuccess(final Method method, final Void response) {
                        refreshMovies();
                    }
                });
            }
        });
    }

    /**
     * Add movie to the server
     *
     * @param movie
     */
    private void addMovie(final Movie movie) {
        adminService.addMovie(movie, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                movieTitleTextBox.setText("");
                refreshMovies();
            }
        });
    }

}