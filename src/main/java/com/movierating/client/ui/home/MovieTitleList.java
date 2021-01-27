package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.Date;

public class MovieTitleList extends Composite {
    interface MovieTitleListUiBinder extends UiBinder<HTMLPanel, MovieTitleList> {
    }

    private static MovieTitleListUiBinder ourUiBinder = GWT.create(MovieTitleListUiBinder.class);

//    @UiField
//    VerticalPanel bestMoviesPanel;

    interface MyStyle extends CssResource {

        @ClassName("releases-header")
        String releasesHeader();

        String viewAllButton();

        String h2();

        @ClassName("best-movies-list")
        String bestMoviesList();

        @ClassName("best-movies-list-cell-title")
        String bestMoviesListCellTitle();

        @ClassName("best-movies-list-cell-global")
        String bestMoviesListCellGlobal();
    }

    @UiField
    FlexTable bestMoviesTable;

    @UiField
    Button viewAllCur;

    @UiField
    HeadingElement bestMoviesHeader;

    @UiField
    MyStyle style;


    public MovieTitleList() {
        initWidget(ourUiBinder.createAndBindUi(this));
        int currentYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(new Date()));
        bestMoviesHeader.setInnerText("Best Movies " + currentYear);
        bestMoviesTable.setText(0,0,"rank");
        bestMoviesTable.setText(0,1,"title");
        bestMoviesTable.setText(0,2,"rating");

        bestMoviesTable.setText(1,0,"1");
        bestMoviesTable.setText(1,1,"Wonder Woman 1984");
        bestMoviesTable.setText(1,2,"95");
        bestMoviesTable.getCellFormatter().addStyleName(1,1,style.bestMoviesListCellTitle());
//        bestMoviesPanel.add(new Label("title1"));
//        bestMoviesPanel.add(new Label("title2"));
//        bestMoviesPanel.add(new Label("title3"));
//        bestMoviesPanel.add(new Label("title4"));
    }
}