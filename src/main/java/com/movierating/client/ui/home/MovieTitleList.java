package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.Date;

public class MovieTitleList extends Composite {
    interface MovieTitleListUiBinder extends UiBinder<HTMLPanel, MovieTitleList> {
    }

    private static MovieTitleListUiBinder ourUiBinder = GWT.create(MovieTitleListUiBinder.class);

    @UiField
    VerticalPanel bestMoviesPanel;

    @UiField
    Button viewAllCur;

    @UiField
    HeadingElement bestMoviesHeader;


    public MovieTitleList() {
        initWidget(ourUiBinder.createAndBindUi(this));
        int currentYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
        bestMoviesHeader.setInnerText("Best Movies " + currentYear);
        bestMoviesPanel.add(new Label("title1"));
        bestMoviesPanel.add(new Label("title2"));
        bestMoviesPanel.add(new Label("title3"));
        bestMoviesPanel.add(new Label("title4"));
    }
}