package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.controller.AdminService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.utils.ImageUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;

public class MovieForm extends Composite {
    interface MovieFormUiBinder extends UiBinder<HTMLPanel, MovieForm> {
    }

    private static MovieFormUiBinder ourUiBinder = GWT.create(MovieFormUiBinder.class);

    private static final AdminService adminService = GWT.create(AdminService.class);

    private static Resources resources = GWT.create(Resources.class);

    @UiField
    HeadingElement header;

    @UiField
    Label movieTitleChars;
    @UiField
    TextBox movieTitleTextBox;

    @UiField
    Label movieDescrChars;
    @UiField
    TextArea movieDescrTextArea;

    @UiField
    Label movieGenreChars;
    @UiField
    TextBox movieGenreTextBox;

    @UiField
    Button submitMovieBtn;

    @UiField
    Button removeMovieBtn;

    @UiField
    Image coverImg;

    @UiField
    FileUpload fileUpload;

    @UiField
    InputElement dateElem;

    @UiField
    FormPanel formPanel;

    public MovieForm(final Movie movie, String headerText) {
        initWidget(ourUiBinder.createAndBindUi(this));
        movieTitleTextBox.setText(movie.getTitle());
        movieDescrTextArea.setText(movie.getDescription());
        movieGenreTextBox.setText(movie.getGenre());
        dateElem.setValue(movie.getPremierDateString());
        coverImg.setUrl(ImageUtils.getImageData(movie.getCoverImg()));
        header.setInnerHTML(headerText);

        submitMovieBtn.addClickHandler(event -> {
            final String title = movieTitleTextBox.getText();
            final String description = movieDescrTextArea.getText();
            final String genre = movieGenreTextBox.getText();
            final String dateString = dateElem.getValue();
            if (!(title.isEmpty() || description.isEmpty() || dateString.isEmpty() || genre.isEmpty())) {
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
                Date date = dateTimeFormat.parse(dateString);
//                String filename = fileUpload.getFilename();
                byte[] imgBytes = null;
//                if (!filename.isEmpty()) {
//                    imgBytes = Base64Utils.fromBase64(filename);
//                }
                imgBytes = ImageUtils.getBytesImage(coverImg.getUrl());
                updateMovie(new Movie(movie.getId(), title, description, genre, date, imgBytes));
                History.newItem("admin");
            }
        });

        removeMovieBtn.addClickHandler(event -> {
            movieTitleTextBox.setText("");
            movieDescrTextArea.setText("");
            movieGenreTextBox.setText("");
            removeMovie(movie);
        });
        movieDescrTextArea.getElement().setAttribute("maxlength", "255");
        initTextBoxLenHandler();
        initTextBoxCharLen();



//        final String[] image = new String[1];
//        fileUpload.addChangeHandler(event -> {
//            String fileName = fileUpload.getFilename();
//            image[0] = loadImage(event.getNativeEvent());
//            GWT.log(fileName);
//        });;
    }

    public MovieForm(String headerText) {
        initWidget(ourUiBinder.createAndBindUi(this));
        coverImg.setResource(resources.emptyCover());
        removeMovieBtn.setVisible(false);
        submitMovieBtn.addClickHandler(event -> {
            final String title = movieTitleTextBox.getText().trim();
            final String description = movieDescrTextArea.getText().trim();
            final String genre = movieGenreTextBox.getText().trim();
            final String dateString = dateElem.getValue();
            if (!(title.isEmpty() || description.isEmpty() || dateString.isEmpty() || genre.isEmpty())) {
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
                Date date = dateTimeFormat.parse(dateString);
                addMovie(new Movie(title, description, genre, date));
            } else{
                return;
            }
            formPanel.submit();
        });
        header.setInnerHTML(headerText);
        movieDescrTextArea.getElement().setAttribute("maxlength", "255");
        initTextBoxLenHandler();
        initTextBoxCharLen();

        // FORM PANEL
        formPanel.setAction(" http://127.0.0.1:8080/admin/movies2");
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);
        formPanel.addSubmitHandler( event -> {
            if (movieTitleTextBox.getText().length() == 0) {
                Window.alert("The text box must not be empty");
                event.cancel();
            }
        });

        //******************
    }

    private void initTextBoxLenHandler() {
        movieTitleTextBox.addKeyUpHandler(event -> {
            String titleLen = String.valueOf(movieTitleTextBox.getText().length());
            movieTitleChars.setText(titleLen + "/255");
        });
        movieDescrTextArea.addKeyUpHandler(event -> {
            String descrLen = String.valueOf(movieDescrTextArea.getText().length());
            movieDescrChars.setText(descrLen + "/255");
        });

        movieGenreTextBox.addKeyUpHandler(event -> {
            String genreLen = String.valueOf(movieGenreTextBox.getText().length());
            movieGenreChars.setText(genreLen + "/255");
        });
    }
    private void initTextBoxCharLen() {
        String titleLen = String.valueOf(movieTitleTextBox.getText().length());
        String descrLen = String.valueOf(movieDescrChars.getText().length());
        String genreLen = String.valueOf(movieGenreTextBox.getText().length());
        movieTitleChars.setText(titleLen + "/255");
        movieDescrChars.setText(descrLen + "/255");
        movieGenreChars.setText(genreLen + "/255");
    }

//    private void setClickEventCoverImage() {
//        fileUpload = new FileUpload();
//        fileUpload.setVisible(false);
//        fileUpload.addChangeHandler(event -> {
//            String fileName = fileUpload.getFilename();
//            GWT.log(fileName);
//        });
//        coverImg.addClickHandler(event -> {
//            fileUpload.click();
//        });
//    }

//    private native String loadImage(NativeEvent event) /*-{
//
//    var image = event.target.files[0];
//
//    // Check if file is an image
//    if (image.type.match('image.*')) {
//
//        var reader = new FileReader();
//        reader.onload = function(e) {
//            // Call-back Java method when done
//            imageLoaded(e.target.result);
//        }
//
//        // Start reading the image
//        reader.readAsDataURL(image);
//    }
//}-*/;

//    @UiHandler("logoUpload")
//    void logoSelected(ChangeEvent e) {
//        if (logoUpload.getFilename() != null && !logoUpload.getFilename().isEmpty()) {
//            loadImage(e.getNativeEvent());
//        }
//    }

    /**
     * Add movie to the server
     *
     * @param movie
     */
    private void addMovie(final Movie movie) {
        adminService.addMovie(movie, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {
                GWT.log(exception.getMessage());
            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                Window.alert("Movies has been saved");
                movieTitleTextBox.setText("");
                movieDescrTextArea.setText("");
                movieGenreTextBox.setText("");
            }
        });
    }

    /**
     * Update movie to the server
     *
     * @param movie
     */
    private void updateMovie(final Movie movie) {
        adminService.updateMovie(movie.getId(), movie, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {
                GWT.log(exception.getMessage());
            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                movieTitleTextBox.setText("");
                movieDescrTextArea.setText("");
                movieGenreTextBox.setText("");
                Window.alert("Movies has been updated");
            }
        });
    }

    /**
     * Remove movie from the server
     *
     * @param movieToRemove
     */
    private void removeMovie(final Movie movieToRemove) {
        adminService.deleteMovie(movieToRemove, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                Window.alert("Movies has been removed");
            }
        });
    }
}