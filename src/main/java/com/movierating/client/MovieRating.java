package com.movierating.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.movierating.client.config.Pages;
import com.movierating.client.ui.admin.AdminPanel;
import com.movierating.client.ui.header.Header;
import com.movierating.client.ui.home.HomePage;
import com.movierating.client.ui.movie.MovieFormPanel;
import org.fusesource.restygwt.client.Defaults;

public class MovieRating implements EntryPoint, ValueChangeHandler {


    public void onModuleLoad() {
        useCorrectRequestBaseUrl();
        RootPanel.get("content").add(new AdminPanel());
        RootPanel.get("header").add(new Header());

//        Hyperlink h1 = new Hyperlink("books","newpage");
//        RootPanel.get("newpage").add(h1);

        History.addValueChangeHandler(this);
        if (History.getToken().isEmpty()) {
            History.newItem(Pages.getAdminPage());
        } else {
            changePage(History.getToken());
        }
    }

    public void onValueChange(ValueChangeEvent event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String historyToken = History.getToken();
        if (historyToken.equals(Pages.getCreateMovie())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new MovieFormPanel("Create Movie"));
        } else if (historyToken.equals(Pages.getAdminPage())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new AdminPanel());
        }
        else if(historyToken.equals(Pages.getHomePage())) {
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
