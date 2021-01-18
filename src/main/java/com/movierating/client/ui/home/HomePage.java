package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<HTMLPanel, HomePage> {
    }

    private static HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);

    @UiField
    CarouselWidget carousel;

    public HomePage() {
//        carousel =  new CarouselWidget();
        initWidget(ourUiBinder.createAndBindUi(this));
    }


}