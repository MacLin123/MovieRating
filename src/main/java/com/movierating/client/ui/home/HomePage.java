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

public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<HTMLPanel, HomePage> {
    }

    private static final HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);
    private static Resources resources = GWT.create(Resources.class);

    interface MyStyle extends CssResource{

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

//    @UiField(provided = true)
//    PosterMovieList upcomingReleaseMovies;

    @UiField
    MyStyle style;

    public HomePage() {
//        carousel =  new CarouselWidget();
//        ScriptInjector.fromString(Resources.INSTANCE.pureJsCarouselJs().getText())
//                .setWindow(ScriptInjector.TOP_WINDOW)
//                .setRemoveTag(false)
//                .inject();
//        StyleInjector.inject(Resources.INSTANCE.pureJsCarouselCss().getText());

        ScriptInjector.fromString(Resources.INSTANCE.gliderJs().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setRemoveTag(false)
                .inject();
        StyleInjector.inject(Resources.INSTANCE.gliderCss().getText());

        Object glider = addCarousel();
        Element gliderElem = Document.get().getElementById("glider1");

        newReleaseMovies = new PosterMovieList(PosterConfig.POSTER_NEW_RELEASES, glider);
//        upcomingReleaseMovies = new PosterMovieList(PosterConfig.POSTER_UPCOMING);
        initWidget(ourUiBinder.createAndBindUi(this));
        gliderElem.addClassName(style.gliderStyle());

//        jQaddSlickCarousel();
//        addPureJsCarousel();
//        addCarouselScript();
//        addItem(glider);
//        addItem(carousel);
//        newCarousel();
//        carousel.setInnerText("12313213");
    }

//    private native void addSlickCarousel() /*-{
//        if ($doc.readyState != "loading") {
//            var curDiv = $doc.getElementById("content");
//
//            var carousel = $doc.createElement("div");
//            carousel.innerHTML = "<div class=\"slider_demo\">" +
//            "<div> Slide  1 </div>" +
//            "<div> Slide  2 </div>" +
//            "<div> Slide  3 </div>";
//            curDiv.insertAdjacentElement("beforeend", carousel);
//            carousel.slick();
//
//        }
//    }-*/;

//    private native void jQaddSlickCarousel() /*-{
//        if ($doc.readyState != "loading") {
//            var curDiv = $doc.getElementById("content");
//
//            var carousel = $doc.createElement("div");
//            carousel.innerHTML = "<div class=\"my-slider\">" +
//                "<div> Slide  1 </div>" +
//                "<div> Slide  2 </div>" +
//                "<div> Slide  3 </div>";
//            curDiv.insertAdjacentElement("beforeend", carousel);
//            $(".my-slider").slick({
//                infinite: false,
//                slidesToShow: 3,
//                slidesToScroll: 3
//            })
//        }
//    }-*/;

//    private native void addPureJsCarousel() /*-{
//        if ($doc.readyState != "loading") {
//            var curDiv = $doc.getElementById("content");
//
//            var carousel = $doc.createElement("div");
//            carousel.innerHTML = "<div class=\"my-slider\" id=\"demo\">" +
//                "<div class=\"slide\"> Slide  1 </div>" +
//                "<div class=\"slide\"> Slide  2 </div>" +
//                "<div class=\"slide\"> Slide  3 </div>" +
//                "</div>";
//            curDiv.insertAdjacentElement("beforeend", carousel);
//            var pureJsCarousel = new $wnd.PureJSCarousel({
//                carousel: '#demo',
//                slide: '.slide'
//            });
//        }
//    }-*/;


//    private native void addCarouselScript() /*-{
//        var pureJsCarousel = new PureJSCarousel({
//            carousel: '#demo',
//            slide: '.slide'
//        });
//    }-*/;

    private native String addCarousel() /*-{
        if ($doc.readyState != "loading") {
            var curDiv = $doc.getElementById("content");

            var carousel = $doc.createElement("div");
            carousel.innerHTML = "<div id=\"glider1\" class=\"glider-contain\">" +
                "<div class=\"glider\">" +
                "</div>" +
                "<button aria-label=\"Previous\" class=\"glider-prev\">«</button>" +
                "<button aria-label=\"Next\" class=\"glider-next\">»</button>" +
                "<div role=\"tablist\" class=\"dots\"></div>" +
                "</div>";
            curDiv.insertAdjacentElement("beforeend", carousel);
            var glider = new $wnd.Glider($doc.querySelector('.glider'), {
                slidesToShow: 5,
                slidesToScroll: 5,
                arrows: {
                    prev: '.glider-prev',
                    next: '.glider-next'
                }
            });
            return glider;
        }
    }-*/;

    private native void newCarousel() /*-{
        if ($doc.readyState != "loading") {
            new $wnd.Glider($doc.querySelector('.glider'), {
                slidesToShow: auto,
                slidesToScroll: 3,
                arrows: {
                    prev: '.glider-prev',
                    next: '.glider-next'
                }
            });
            return carousel.innerHTML;
        }
    }-*/;

    private native void addItem(Object glider, Element itemToAdd) /*-{
    glider.addItem(element);

    }-*/;
}