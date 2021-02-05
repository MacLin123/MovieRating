package com.movierating.client.ui.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.Pages;
import com.movierating.client.controller.AdminService;
import com.movierating.client.model.Movie;
import com.movierating.client.ui.movie.MovieFormPanel;
import com.movierating.client.ui.movie.MovieLabel;
import com.movierating.client.utils.ImageUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AdminPanel extends Composite {
    interface AdminPanelUiBinder extends UiBinder<HTMLPanel, AdminPanel> {
    }
    interface MyStyle extends CssResource{

        @ClassName("admin-panel")
        String adminPanel();

        String header2();

        @ClassName("img-admin")
        String imgAdmin();

        String center();

        String header();

        String flex_table();

        @ClassName("input-group-search")
        String inputGroupSearch();

        @ClassName("input-group-button")
        String inputGroupButton();

        @ClassName("input-group-search-text")
        String inputGroupSearchText();

        @ClassName("input-group-search-button")
        String inputGroupSearchButton();
    }

    private static AdminPanelUiBinder ourUiBinder = GWT.create(AdminPanelUiBinder.class);

    private static final AdminService adminService = GWT.create(AdminService.class);

    @UiField
    FlexTable movieList;

//    @UiField
//    TextBox movieTitleTextBox;
//
//    @UiField
//    TextArea movieDescrTextArea;
//
//    @UiField
//    TextBox movieGenreTextBox;
//
//    @UiField
//    TextBox movieDateTextBox;

//    @UiField
//    TextBox movieRatingTextBox;

//    @UiField
//    Button addMovieBtn;

    @UiField
    TextBox searchByTitleTextBox;

    @UiField
    Button searchMovieBtn;

    @UiField
    MyStyle style;


    public AdminPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));

        refreshMovies();

//        addMovieBtn.addClickHandler(event -> {
//            final String title = movieTitleTextBox.getText();
//            final String description = movieDescrTextArea.getText();
//            final String genre = movieGenreTextBox.getText();
//            // TODO date filed verifying
//            final String dateString = movieDateTextBox.getText();
//            if (!(title.isEmpty() || description.isEmpty() || dateString.isEmpty())) {
//                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
//                Date date = dateTimeFormat.parse(dateString);
//                addMovie(new Movie(title, description, genre, date));
//            }
//        });

        searchMovieBtn.addClickHandler(event -> {
            refreshMovies();
        });

//        movieTitleTextBox.getElement().setAttribute("placeholder", "title");
//        movieDescrTextArea.getElement().setAttribute("placeholder", "description");
        searchByTitleTextBox.getElement().setAttribute("placeholder", "title to search");
//        movieGenreTextBox.getElement().setAttribute("placeholder", "genre");
//        movieDateTextBox.getElement().setAttribute("placeholder", "2000-01-25");

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
    public void refreshMovies() {
        String searchTitle = searchByTitleTextBox.getText();
        if (searchTitle.isEmpty()) {
            return;
        }
        adminService.searchMovies(searchTitle, new MethodCallback<List<Movie>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                GWT.log(method.getResponse().getText(), exception);
            }

            @Override
            public void onSuccess(Method method, List<Movie> response) {
                movieList.removeAllRows();
                for (final Movie movie : response) {

//                    movieLabel.addClickHandler(movieToRemove -> removeMovie(movieToRemove));
                    int row = movieList.getRowCount();
//                    movieList.setText(row, 0, movie.getTitle());
                    Image img = new Image(ImageUtils.getImageData(movie.getCoverImg()));
                    img.addStyleName(style.imgAdmin());
                    img.addClickHandler(event -> {
                        History.newItem(Pages.UPDATE_MOVIE.getStrValue());
                        RootPanel.get("content").clear();
                        RootPanel.get("content").add(new MovieFormPanel(movie.getId(), "Update Movie"));

                    });
                    movieList.setWidget(row, 0, img);
//                    movieList.setText(row, 2, movie.getDescription());
                    movieList.setWidget(row, 1, new MovieLabel(movie));
                }
            }
        });
    }

//    /**
//     * Add movie to the server
//     *
//     * @param movie
//     */
//    private void addMovie(final Movie movie) {
//        adminService.addMovie(movie, new MethodCallback<Void>() {
//            @Override
//            public void onFailure(final Method method, final Throwable exception) {
//                GWT.log(exception.getMessage());
//            }
//
//            @Override
//            public void onSuccess(final Method method, final Void response) {
////                movieTitleTextBox.setText("");
////                movieDescrTextArea.setText("");
////                movieDateTextBox.setText("");
////                movieGenreTextBox.setText("");
//                refreshMovies();
//            }
//        });
//    }

}