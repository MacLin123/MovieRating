package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.movierating.client.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieLabel extends Composite {
    interface MovieLabelUiBinder extends UiBinder<Label, MovieLabel> {
    }

    private static MovieLabelUiBinder ourUiBinder = GWT.create(MovieLabelUiBinder.class);

    public interface MovieLabelClickHandler {
        void onClick(final Movie movie);
    }

    private final List<MovieLabelClickHandler> clickHandlers;

    @UiField
    Label label;

    public MovieLabel(final Movie movie) {
        initWidget(ourUiBinder.createAndBindUi(this));
        label.setText(movie.getTitle());
        clickHandlers = new ArrayList<>();
        addDomHandler( event -> {
            for (MovieLabelClickHandler clickHandler:clickHandlers){
                clickHandler.onClick(movie);
            }
        }, ClickEvent.getType());
    }
    public void addClickHandler(final MovieLabelClickHandler clickHandler) {
        clickHandlers.add(clickHandler);
    }
}