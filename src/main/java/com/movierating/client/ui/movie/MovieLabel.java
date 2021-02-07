package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.movierating.client.model.Movie;

public class MovieLabel extends Composite {
    interface MovieLabelUiBinder extends UiBinder<HTMLPanel, MovieLabel> {
    }

    private static final MovieLabelUiBinder ourUiBinder = GWT.create(MovieLabelUiBinder.class);

    private static final int MAX_DESCRIPTION_LEN = 150;

    @UiField
    Label title;
    @UiField
    Label description;

    @UiField
    Label year;


    public MovieLabel(final Movie movie) {
        initWidget(ourUiBinder.createAndBindUi(this));

        title.setText(movie.getTitle());
        String descrToInsert = movie.getDescription();
        if (descrToInsert.length() > MAX_DESCRIPTION_LEN) {
            descrToInsert = descrToInsert.substring(0, MAX_DESCRIPTION_LEN) + "...";
        }
        description.setText(descrToInsert);
        String yearStr = "(" + DateTimeFormat.getFormat("yyyy").format(movie.getPremierDate()) + ")";
        year.setText(yearStr);
    }

}