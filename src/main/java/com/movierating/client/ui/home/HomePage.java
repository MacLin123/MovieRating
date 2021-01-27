package com.movierating.client.ui.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.movierating.client.config.PosterConfig;
import com.movierating.client.resources.Resources;
import com.movierating.client.widgets.GliderWrapper;

public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<HTMLPanel, HomePage> {
    }

    private static final HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);

    interface MyStyle extends CssResource {

        @ClassName("glider-style")
        String gliderStyle();

        @ClassName("releases-header")
        String releasesHeader();

        @ClassName("carousel-home")
        String carouselHome();

        @ClassName("movie-lists")
        String movieLists();
    }

    //    @UiField
//    CarouselWidget carousel;
    @UiField(provided = true)
    PosterMovieList newReleaseMovies;

    @UiField(provided = true)
    PosterMovieList upcomingReleaseMovies;

    @UiField
    MyStyle style;

    public HomePage() {
        injectResources();
        newReleaseMovies = new PosterMovieList(PosterConfig.POSTER_NEW_RELEASES);
        upcomingReleaseMovies = new PosterMovieList(PosterConfig.POSTER_UPCOMING);
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    private void injectResources() {
        ScriptInjector.fromString(Resources.INSTANCE.gliderJs().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setRemoveTag(false)
                .inject();
        StyleInjector.inject(Resources.INSTANCE.gliderCss().getText());
    }


//    private native String addCarousel() /*-{
//        if ($doc.readyState != "loading") {
//            var curDiv = $doc.getElementById("content");
//
//            var carousel = $doc.createElement("div");
//            carousel.className = "glider-contain multiple";
//            carousel.id = "glider1";
//            carousel.innerHTML = "<div class=\"glider\">" +
//                "</div>" +
//                "<button aria-label=\"Previous\" class=\"glider-prev\">«</button>" +
//                "<button aria-label=\"Next\" class=\"glider-next\">»</button>" +
//                "<div role=\"tablist\" class=\"dots\"></div>";
//            curDiv.insertAdjacentElement("beforeend", carousel);
//            var glider = new $wnd.Glider($doc.querySelector('.glider'), {
//                slidesToShow: 5,
//                slidesToScroll: 5,
//                srollLock: true,
//                arrows: {
//                    prev: '.glider-prev',
//                    next: '.glider-next'
//                }
//            });
//            return glider;
//        }
//    }-*/;

//    private native void addItem(Object glider, Element itemToAdd) /*-{
//        glider.addItem(element);
//
//    }-*/;
}