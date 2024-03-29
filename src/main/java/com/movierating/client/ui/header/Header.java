package com.movierating.client.ui.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.movierating.client.config.Pages;

public class Header extends Composite {
    interface HeaderUiBinder extends UiBinder<HTMLPanel, Header> {
    }

    private static final HeaderUiBinder ourUiBinder = GWT.create(HeaderUiBinder.class);

    @UiField
    UListElement header;

    @UiField
    Hyperlink home;

    @UiField
    Hyperlink admin;

    @UiField
    Hyperlink createMovie;


    public Header() {
        initWidget(ourUiBinder.createAndBindUi(this));
        home.setTargetHistoryToken(Pages.HOME.getStrValue());
        admin.setTargetHistoryToken(Pages.ADMIN_SEARCH_PANEL.getStrValue());
        createMovie.setTargetHistoryToken(Pages.CREATE_MOVIE.getStrValue());
    }
}