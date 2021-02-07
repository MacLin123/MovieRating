package com.movierating.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.movierating.client.config.Pages;
import com.movierating.client.config.SearchPanelConfig;
import com.movierating.client.ui.admin.SearchPanel;
import com.movierating.client.ui.header.Header;
import com.movierating.client.ui.home.HomePage;
import com.movierating.client.ui.movie.MovieFormPanel;
import org.fusesource.restygwt.client.Defaults;

public class MovieRating implements EntryPoint, ValueChangeHandler {

    public void onModuleLoad() {
        useCorrectRequestBaseUrl();

        RootPanel.get("header").add(new Header());

        History.addValueChangeHandler(this);
        if (History.getToken().isEmpty()) {
            History.newItem(Pages.HOME.getStrValue());
        } else {
            changePage(History.getToken());
        }
    }

    public void onValueChange(ValueChangeEvent event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String historyToken = token;
        if (historyToken.equals(Pages.CREATE_MOVIE.getStrValue())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new MovieFormPanel("Create Movie"));
        } else if (historyToken.equals(Pages.ADMIN_SEARCH_PANEL.getStrValue())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new SearchPanel(SearchPanelConfig.ADMIN_SEARCH));
        } else if (historyToken.equals(Pages.HOME.getStrValue())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new HomePage());
        }
    }

    private void useCorrectRequestBaseUrl() {
        if (isDevelopmentMode()) {
            Defaults.setServiceRoot("http://127.0.0.1:8080");
        } else {
            Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        }
    }

    private static native boolean isDevelopmentMode()/*-{
        return typeof $wnd.__gwt_sdm !== 'undefined';
    }-*/;

}
