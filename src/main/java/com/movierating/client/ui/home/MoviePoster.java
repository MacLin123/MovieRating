package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.resources.styles.MovieScoreStyle;
import com.movierating.client.utils.ScoreUtils;

public class MoviePoster extends Composite implements HasClickHandlers {

    interface MoviePosterUiBinder extends UiBinder<HTMLPanel, MoviePoster> {
    }

    private static final MoviePosterUiBinder ourUiBinder = GWT.create(MoviePosterUiBinder.class);

    @UiField
    Image posterImage;

    @UiField
    DivElement imgDiv;

    @UiField
    DivElement ratingDiv;

    @UiField
    DivElement titleDiv;

    @UiField
    Label titleLabel;

    @UiField
    MovieScoreStyle movieScoreStyle;

    public MoviePoster() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


    public MoviePoster(Image image,String title, Integer rating) {

        initWidget(ourUiBinder.createAndBindUi(this));

        this.posterImage.setUrl(image.getUrl());
        titleLabel.setText(title);
        if(rating == null) {
            return;
        }
        ScoreUtils.initRatingMarkStyle(rating,ratingDiv,movieScoreStyle);
        ratingDiv.setInnerText(String.valueOf(rating));
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }
}