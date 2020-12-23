package com.movierating.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;

public class MovieRating implements EntryPoint {
    private FlexTable empFlexTable = new FlexTable();

    public void onModuleLoad() {
        useCorrectRequestBaseUrl();
        empFlexTable.setText(0, 0, "Id");
        empFlexTable.setText(0, 1, "Name");
        empFlexTable.setText(0, 2, "Role");
        empFlexTable.setText(0, 3, "Remove");
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
