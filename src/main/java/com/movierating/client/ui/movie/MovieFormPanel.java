package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.FileConfig;
import com.movierating.client.controller.AdminService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class MovieFormPanel extends Composite {
    interface MovieFormPanelUiBinder extends UiBinder<HTMLPanel, MovieFormPanel> {
    }

    private static MovieFormPanelUiBinder ourUiBinder = GWT.create(MovieFormPanelUiBinder.class);

    private static Resources resources = GWT.create(Resources.class);

    private static final AdminService adminService = GWT.create(AdminService.class);
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

    public MovieFormPanel(String headerText) {
        initWidget(ourUiBinder.createAndBindUi(this));

        coverImg.setResource(resources.emptyCover());

        formPanel.setAction(" http://127.0.0.1:8080/admin/movies2");
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);

        formPanel.addSubmitHandler(event -> {
            final String title = movieTitleTextBox.getText().trim();
            final String description = movieDescrTextArea.getText().trim();
            final String genre = movieGenreTextBox.getText().trim();
            final String dateString = dateElem.getValue();
            if (title.isEmpty() || description.isEmpty() || dateString.isEmpty() || genre.isEmpty()) {
                Window.alert("All fields except cover photo should be filled");
                event.cancel();
            }
        });

        submitMovieBtn.addClickHandler(event -> {
            int size  = getFileSize(fileUpload.getElement());
            if (size > FileConfig.MAX_FILE_SIZE.getValue()) {
                Window.alert("Max size of file is 5MB");
                return;
            }
            movieDateTextBox.setText(dateElem.getValue());
            formPanel.submit();
        });

        movieDateTextBox.setVisible(false);
        removeMovieBtn.setVisible(false);
        movieDescrTextArea.getElement().setAttribute("maxlength", "255");
        header.setInnerHTML(headerText);

        initTextBoxLenHandler();
        initTextBoxCharLen();


        formPanel.addSubmitCompleteHandler(event -> {
            String results = event.getResults();
            Window.alert((results == null) ? "Success" : results);
        });
    }

    private native int getFileSize(final Element data) /*-{
        return data.files[0].size;
    }-*/;
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
//
//public class FormPanelExample implements EntryPoint {
//
//    public void onModuleLoad() {
//        // Create a FormPanel and point it at a service.
//        final FormPanel form = new FormPanel();
//        form.setAction("/myFormHandler");
//
//        // Because we're going to add a FileUpload widget, we'll need to set the
//        // form to use the POST method, and multipart MIME encoding.
//        form.setEncoding(FormPanel.ENCODING_MULTIPART);
//        form.setMethod(FormPanel.METHOD_POST);
//
//        // Create a panel to hold all of the form widgets.
//        VerticalPanel panel = new VerticalPanel();
//        form.setWidget(panel);
//
//        // Create a TextBox, giving it a name so that it will be submitted.
//        final TextBox tb = new TextBox();
//        tb.setName("textBoxFormElement");
//        panel.add(tb);
//
//        // Create a ListBox, giving it a name and some values to be associated with
//        // its options.
//        ListBox lb = new ListBox();
//        lb.setName("listBoxFormElement");
//        lb.addItem("foo", "fooValue");
//        lb.addItem("bar", "barValue");
//        lb.addItem("baz", "bazValue");
//        panel.add(lb);
//
//        // Create a FileUpload widget.
//        FileUpload upload = new FileUpload();
//        upload.setName("uploadFormElement");
//        panel.add(upload);
//
//        // Add a 'submit' button.
//        panel.add(new Button("Submit", new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                form.submit();
//            }
//        }));
//
//        // Add an event handler to the form.
//        form.addSubmitHandler(new FormPanel.SubmitHandler() {
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                // This event is fired just before the form is submitted. We can take
//                // this opportunity to perform validation.
//                if (tb.getText().length() == 0) {
//                    Window.alert("The text box must not be empty");
//                    event.cancel();
//                }
//            }
//        });
//        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                // When the form submission is successfully completed, this event is
//                // fired. Assuming the service returned a response of type text/html,
//                // we can get the result text here (see the FormPanel documentation for
//                // further explanation).
//                Window.alert(event.getResults());
//            }
//        });
//
//        RootPanel.get().add(form);
//    }
//
