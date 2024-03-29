package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.FileConfig;
import com.movierating.client.config.Pages;
import com.movierating.client.controller.AdminService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.utils.ImageUtils;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class MovieFormPanel extends Composite {
    interface MovieFormPanelUiBinder extends UiBinder<HTMLPanel, MovieFormPanel> {
    }

    private static final MovieFormPanelUiBinder ourUiBinder = GWT.create(MovieFormPanelUiBinder.class);

    private static final AdminService adminService = GWT.create(AdminService.class);
    private static final String URL_REQUEST_CREATE = Defaults.getServiceRoot() + "admin/movies/create";
    private static final String URL_REQUEST_UPDATE = Defaults.getServiceRoot() + "admin/movies/update";
    @UiField
    HeadingElement header;

    @UiField
    Label titleLabel;
    @UiField
    Label movieTitleChars;
    @UiField
    TextBox movieTitleTextBox;

    @UiField
    Label descrLabel;
    @UiField
    Label movieDescrChars;
    @UiField
    TextArea movieDescrTextArea;

    @UiField
    Label genreLabel;
    @UiField
    Label movieGenreChars;
    @UiField
    TextBox movieGenreTextBox;

    @UiField
    Label youtubeIdLabel;
    @UiField
    Label youtubeIdChars;
    @UiField
    TextBox youtubeIdTextBox;

    @UiField
    Label dateLabel;
    @UiField
    InputElement dateElem;
    @UiField
    TextBox movieDateTextBox;

    @UiField
    Label coveImgLabel;
    @UiField
    Image coverImg;

    @UiField
    Button submitMovieBtn;

    @UiField
    Button removeMovieBtn;

    @UiField
    FormPanel formPanel;

    @UiField
    VerticalPanel panel;

    @UiField
    FileUpload fileUpload;

    /**
     * Update movie constructor
     *
     * @param id         id of movie
     * @param headerText main header text of movie update panel
     */
    public MovieFormPanel(final Long id, String headerText) {
        getMovie(id);
        initCommonFormElements();
        header.setInnerHTML(headerText);

        initTextBoxLenHandler();
        // The result html can be null as a result of submitting a form to a different domain.
        formPanel.addSubmitCompleteHandler(event -> {
            String results = event.getResults();
            Window.alert((results != null) ? results : "Submitted");
            History.newItem(Pages.ADMIN_SEARCH_PANEL.getStrValue());
        });
    }

    /**
     * Create movie constructor
     *
     * @param headerText main header text of movie create panel
     */
    public MovieFormPanel(String headerText) {
        initCommonFormElements();
        formPanel.setAction(URL_REQUEST_CREATE);
        header.setInnerHTML(headerText);
        coverImg.setResource(Resources.INSTANCE.emptyCover());

        removeMovieBtn.setVisible(false);
        initTextBoxLenHandler();
        initTextBoxCharLen();


        // The result html can be null as a result of submitting a form to a different domain.
        formPanel.addSubmitCompleteHandler(event -> {
            String results = event.getResults();
            Window.alert((results != null) ? results : "Submitted");
        });
    }

    private native int getFileSize(final Element data) /*-{
        if (data.files[0] != null) {
            return data.files[0].size;
        } else {
            return 0;
        }
    }-*/;

    private native boolean fileValidation(final Element fileInput) /*-{

        var filePath = fileInput.value;

        // Allowing file type
        var allowedExtensions =
            /(\.jpg|\.jpeg|\.png|\.gif)$/i;

        if (!allowedExtensions.exec(filePath)) {
            fileInput.value = '';
            return false;
        }
        return true;
    }-*/;

    private void initCommonFormElements() {
        initWidget(ourUiBinder.createAndBindUi(this));

        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);

        movieDateTextBox.setVisible(false);
        movieDescrTextArea.getElement().setAttribute("maxlength", "500");
        initSubmitButtonHandler();

        formPanel.addSubmitHandler(event -> {
            final String title = movieTitleTextBox.getText().trim();
            final String description = movieDescrTextArea.getText().trim();
            final String genre = movieGenreTextBox.getText().trim();
            final String dateString = dateElem.getValue();
            if (title.isEmpty() || description.isEmpty() || dateString.isEmpty() || genre.isEmpty()) {
                Window.alert("All fields except cover photo and youtubeId should be filled");
                event.cancel();
            }
        });
    }

    private void initSubmitButtonHandler() {
        submitMovieBtn.addClickHandler(event -> {
            int size = getFileSize(fileUpload.getElement());
            if (size > FileConfig.MAX_FILE_SIZE.getValue()) {
                Window.alert("Max size of file is 5MB");
                return;
            }
            if ((size != 0) && !fileValidation(fileUpload.getElement())) {
                Window.alert("Invalid file extension, you should upload an image");
                return;
            }
            movieDateTextBox.setText(dateElem.getValue());
            formPanel.submit();
        });
    }

    private void initTextBoxLenHandler() {
        movieTitleTextBox.addKeyUpHandler(event -> {
            String titleLen = String.valueOf(movieTitleTextBox.getText().length());
            movieTitleChars.setText(titleLen + "/255");
        });
        movieDescrTextArea.addKeyUpHandler(event -> {
            String descrLen = String.valueOf(movieDescrTextArea.getText().length());
            movieDescrChars.setText(descrLen + "/500");
        });

        movieGenreTextBox.addKeyUpHandler(event -> {
            String genreLen = String.valueOf(movieGenreTextBox.getText().length());
            movieGenreChars.setText(genreLen + "/255");
        });

        youtubeIdTextBox.addKeyUpHandler(event -> {
            String genreLen = String.valueOf(youtubeIdTextBox.getText().length());
            youtubeIdChars.setText(genreLen + "/255");
        });
    }

    private void initTextBoxCharLen() {
        String titleLen = String.valueOf(movieTitleTextBox.getText().length());
        String descrLen = String.valueOf(movieDescrTextArea.getText().length());
        String genreLen = String.valueOf(movieGenreTextBox.getText().length());
        String youtubeIdLen = String.valueOf(youtubeIdTextBox.getText().length());
        movieTitleChars.setText(titleLen + "/255");
        movieDescrChars.setText(descrLen + "/500");
        movieGenreChars.setText(genreLen + "/255");
        youtubeIdChars.setText(youtubeIdLen + "/255");
    }


    private void getMovie(Long id) {
        adminService.getMovie(id, new MethodCallback<Movie>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, Movie movie) {
                formPanel.setAction(URL_REQUEST_UPDATE + "/" + movie.getId());
                movieTitleTextBox.setText(movie.getTitle());
                movieDescrTextArea.setText(movie.getDescription());
                movieGenreTextBox.setText(movie.getGenre());
                youtubeIdTextBox.setText(movie.getYoutubeId());
                dateElem.setValue(movie.getPremierDateString());
                coverImg.setUrl(ImageUtils.getImageData(movie.getCoverImg()));


                removeMovieBtn.addClickHandler(event -> {
                    movieTitleTextBox.setText("");
                    movieDescrTextArea.setText("");
                    movieGenreTextBox.setText("");
                    youtubeIdTextBox.setText("");
                    removeMovie(movie.getId());
                });
                initTextBoxCharLen();
            }
        });
    }

    /**
     * Remove movie from the server
     *
     * @param idMovieToRemove id of movie you want to remove
     */
    private void removeMovie(final Long idMovieToRemove) {
        adminService.deleteMovie(idMovieToRemove, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {
                GWT.log(method.getResponse().getText(), exception);
            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                Window.alert(method.getResponse().getText());
                History.newItem(Pages.ADMIN_SEARCH_PANEL.getStrValue());
            }
        });
    }
}