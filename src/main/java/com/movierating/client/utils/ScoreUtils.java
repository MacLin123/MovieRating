package com.movierating.client.utils;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.user.client.ui.Label;
import com.movierating.client.resources.styles.MovieScoreStyle;

public class ScoreUtils {
    public static void initRatingMarkStyle(Integer rating, DivElement ratingDiv, MovieScoreStyle movieScoreStyle) {
        if (rating >= 70) {
            ratingDiv.addClassName(movieScoreStyle.positive());
        } else if (rating < 50) {
            ratingDiv.addClassName(movieScoreStyle.negative());
        } else {
            ratingDiv.addClassName(movieScoreStyle.mixed());
        }
    }

    public static void initRatingMarkStyle(Integer rating, Label ratingLabel, MovieScoreStyle movieScoreStyle) {
        if (rating == null) return;
        if (rating >= 70) {
            ratingLabel.addStyleName(movieScoreStyle.positive());
        } else if (rating < 50) {
            ratingLabel.addStyleName(movieScoreStyle.negative());
        } else {
            ratingLabel.addStyleName(movieScoreStyle.mixed());
        }
    }
}
