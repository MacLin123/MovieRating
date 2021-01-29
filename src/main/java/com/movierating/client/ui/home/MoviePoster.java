package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.resources.styles.MovieScoreStyle;

public class MoviePoster extends Composite {
    interface MoviePosterUiBinder extends UiBinder<HTMLPanel, MoviePoster> {
    }

    private static MoviePosterUiBinder ourUiBinder = GWT.create(MoviePosterUiBinder.class);

    interface MyStyle extends CssResource {

        String img_wrapper();


        String poster();

        String title_wrapper();
    }

    @UiField
    MyStyle style;

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
        initRatingStyle(rating);
        ratingDiv.setInnerText(String.valueOf(rating));


//        posterPanel.add(image);
//        posterPanel.add(new Label(title));
//        posterPanel.add(new Label(title));
//        posterPanel.insert(image, DockLayoutPanel.Direction.SOUTH,50,new Label(title));
    }

    private void initRatingStyle(Integer rating) {
        if (rating >= 70){
            ratingDiv.addClassName(movieScoreStyle.positive());
        }else if (rating < 50) {
            ratingDiv.addClassName(movieScoreStyle.negative());
        } else{
            ratingDiv.addClassName(movieScoreStyle.mixed());
        }
    }
}