package com.movierating.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
    Resources INSTANCE = GWT.create(Resources.class);
    @Source("images/no_img.jpg")
    ImageResource emptyCover();

//    @Source("slick/slick.js")
//    TextResource slickJs();

//    @Source("slick/jquery-2.2.0.min.js")
//    TextResource jQuery();

//    @Source("slick/slick.css")
//    TextResource slickCss();

//    @Source("slick/slick-theme.css")
//    TextResource slickThemeCss();

    //Pure js carousel
    @Source("PureJSCarousel/purejscarousel.js")
    TextResource pureJsCarouselJs();

    @Source("PureJSCarousel/purejscarousel.css")
    TextResource pureJsCarouselCss();


    @Source("Glider/glider.js")
    TextResource gliderJs();

    @Source("Glider/glider.min.css")
    TextResource gliderCss();


}
