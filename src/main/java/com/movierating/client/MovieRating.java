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
import com.movierating.client.ui.movie.MovieForm;
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
//            RootPanel.get("admin-panel").clear();

//            NodeList<Node> nodeList = RootPanel.get("content").getElement().getChildNodes();
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                if (nodeList.getItem(i) instanceof Element){
//                    Element elem = (Element) nodeList.getItem(i);
//                    elem.setInnerHTML("");
//                }
//            }
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new MovieForm("Create Movie"));
        } else if (historyToken.equals(Pages.getAdminPage())) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new AdminPanel());
        } else if(historyToken.equals("create2")) {
            RootPanel.get("content").clear();
            RootPanel.get("content").add(new MovieFormPanel("Create Movie"));
        }
//        else if(historyToken.startsWith("update-movie/")) {
//            RootPanel.get("content").clear();
//            RootPanel.get("content").add(new MovieForm(movie, "Update Movie"));
//        }
    }

    private void useCorrectRequestBaseUrl() {
        if (isDevelopmentMode()) {
            Defaults.setServiceRoot("http://localhost:8080");
        } else {
            Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        }
    }

    private static native boolean isDevelopmentMode()/*-{
        return typeof $wnd.__gwt_sdm !== 'undefined';
    }-*/;
}
