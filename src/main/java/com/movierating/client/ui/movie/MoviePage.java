package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.movierating.client.resources.Resources;
import com.movierating.client.resources.styles.MovieScoreStyle;
import com.movierating.client.utils.ImageUtils;

public class MoviePage extends Composite {
    interface MoviePageUiBinder extends UiBinder<HTMLPanel, MoviePage> {
    }

    private static MoviePageUiBinder ourUiBinder = GWT.create(MoviePageUiBinder.class);

    @UiField
    Image coverMedium;

    @UiField
    MovieScoreStyle movieScoreStyle;

    @UiField
    DivElement ratingDiv;
    public MoviePage() {
        initWidget(ourUiBinder.createAndBindUi(this));
        coverMedium.setResource(Resources.INSTANCE.emptyCover());
        ratingDiv.addClassName(movieScoreStyle.positive());
        ratingDiv.setInnerText("31");
    }
}