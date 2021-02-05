//package com.movierating.client.ui.movie;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.i18n.client.DateTimeFormat;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.user.client.ui.*;
//import com.movierating.client.controller.AdminService;
//import com.movierating.client.model.Movie;
//import com.movierating.client.ui.admin.SearchPanel;
//import org.fusesource.restygwt.client.Method;
//import org.fusesource.restygwt.client.MethodCallback;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class MoviePopUp extends PopupPanel {
//    interface MoviePopUpUiBinder extends UiBinder<HTMLPanel, MoviePopUp> {
//    }
//
//    private static final MoviePopUpUiBinder ourUiBinder = GWT.create(MoviePopUpUiBinder.class);
//
//    private static final AdminService adminService = GWT.create(AdminService.class);
//
//    //    public interface MoviePopUpClickHandler {
////        void onClick(final Movie movie);
////    }
////    private final List<MoviePopUpClickHandler> clickHandlers;
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
//
////    @UiField
////    TextBox movieRatingTextBox;
//
//    @UiField
//    Button updateMovieBtn;
//
//    @UiField
//    Button removeMovieBtn;
//
//    @UiField
//    Button closePopUpBtn;
//
////    List<ClickHandler> clickHandlers = new ArrayList<>();
////    private static SearchPanel adminPanel;
//
//    public MoviePopUp(final Movie movie) {
//
//        GWT.log(String.valueOf(movie.getId()));
//        setWidget(ourUiBinder.createAndBindUi(this));
//        this.setAutoHideEnabled(true);
//
//        movieTitleTextBox.setText(movie.getTitle());
//        movieDescrTextArea.setText(movie.getDescription());
//        movieGenreTextBox.setText(movie.getGenre());
//        movieDateTextBox.setText(movie.getPremierDateString());
//
////        adminPanel = SearchPanel.getInstance();
//
//        removeMovieBtn.addClickHandler(event -> {
//            movieTitleTextBox.setText("");
//            movieDescrTextArea.setText("");
//            movieGenreTextBox.setText("");
//            movieDateTextBox.setText("");
//            removeMovie(movie);
//        });
//
//        //Click Handlers
////        clickHandlers = new ArrayList<>();
////        addDomHandler( event -> {
////            for (MoviePopUpClickHandler clickHandler:clickHandlers){
////                clickHandler.onClick(movie);
////            }
////        }, ClickEvent.getType());
//
////        refreshMovies();
//
//        updateMovieBtn.addClickHandler(event -> {
//            final String title = movieTitleTextBox.getText();
//            final String description = movieDescrTextArea.getText();
//            final String genre = movieGenreTextBox.getText();
//            // TODO date filed verifying
//            final String dateString = movieDateTextBox.getText();
//            if (!(title.isEmpty() || description.isEmpty() || dateString.isEmpty())) {
//                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
//                Date date = dateTimeFormat.parse(dateString);
//                updateMovie(new Movie(movie.getId(), title, description, genre, date));
//            }
//        });
//
//        movieTitleTextBox.getElement().setAttribute("placeholder", "title");
//        movieDescrTextArea.getElement().setAttribute("placeholder", "description");
//        movieGenreTextBox.getElement().setAttribute("placeholder", "genre");
//        movieDateTextBox.getElement().setAttribute("placeholder", "2000-01-25");
//    }
//
//    /**
//     * update movie to the server
//     *
//     * @param movie
//     */
//    private void updateMovie(final Movie movie) {
//        adminService.updateMovie(movie.getId(), movie, new MethodCallback<Void>() {
//            @Override
//            public void onFailure(final Method method, final Throwable exception) {
//                GWT.log(exception.getMessage());
//            }
//
//            @Override
//            public void onSuccess(final Method method, final Void response) {
//                movieTitleTextBox.setText("");
//                movieDescrTextArea.setText("");
//                movieDateTextBox.setText("");
//                movieGenreTextBox.setText("");
//                closePopUpBtn.click();
////                hide();
//            }
//        });
//    }
//
//    /**
//     * Remove movie from the server
//     *
//     * @param movieToRemove
//     */
//    private void removeMovie(final Movie movieToRemove) {
//        adminService.deleteMovie(movieToRemove, new MethodCallback<Void>() {
//            @Override
//            public void onFailure(final Method method, final Throwable exception) {
//
//            }
//
//            @Override
//            public void onSuccess(final Method method, final Void response) {
//                closePopUpBtn.click();
////                hide();
//            }
//        });
//    }
//    public void addCloseClickHandler(ClickHandler clickHandler) {
//        closePopUpBtn.addClickHandler(clickHandler);
//    }
//}