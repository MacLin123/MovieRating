package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;

public class MoviePopUp extends Composite {
    interface MoviePopUpUiBinder extends UiBinder<com.google.gwt.user.client.ui.HTMLPanel, com.movierating.client.ui.movie.MoviePopUp> {
    }

    private static MoviePopUpUiBinder ourUiBinder = GWT.create(MoviePopUpUiBinder.class);

    public MoviePopUp() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}