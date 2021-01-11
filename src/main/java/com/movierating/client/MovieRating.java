package com.movierating.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.ui.admin.AdminPanel;
import org.fusesource.restygwt.client.Defaults;

public class MovieRating implements EntryPoint, ValueChangeHandler {


    public void onModuleLoad() {
        useCorrectRequestBaseUrl();
        RootPanel.get("admin-panel").add(new AdminPanel());
//        RootPanel.get("newpage").add(new AdminPanel());
        Hyperlink h1 = new Hyperlink("books","newpage");
        RootPanel.get("newpage").add(h1);
        History.addValueChangeHandler(this);
        //when there is no token, the "home" token is set else changePage() is called.
        //this is useful if a user has bookmarked a site other than the homepage.
        if(History.getToken().isEmpty()){
            History.newItem("home");
        } else {
            changePage(History.getToken());
        }
    }
    public void onValueChange(ValueChangeEvent event) {
        changePage(History.getToken());
    }
    public void changePage(String token) {
        if(History.getToken().equals("newpage")) {
//            RootPanel.get("admin-panel").clear();

            NodeList<Node> nodeList = RootPanel.get("content").getElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.getItem(i) instanceof Element){
                    Element elem = (Element) nodeList.getItem(i);
                    elem.setInnerHTML("");
                }
            }
            RootPanel.get("admin-panel").add(new AdminPanel());
        }
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
