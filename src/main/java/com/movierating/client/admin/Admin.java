package com.movierating.client.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

public class Admin {
    interface AdminUiBinder extends UiBinder<DivElement, Admin> {
    }

    private static AdminUiBinder ourUiBinder = GWT.create(AdminUiBinder.class);

    public Admin() {
        DivElement rootElement = ourUiBinder.createAndBindUi(this);
    }
}