package com.movierating.client.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.movierating.client.config.Pages;
import com.movierating.client.ui.movie.MoviePage;

public class GliderWrapper {
    private Object glider;
    private Element gliderElem;

    public GliderWrapper() {

    }

    public native void createGlider(Element whereToInsert) /*-{
        if ($doc.readyState != "loading") {
            var id = @Sequence::getNextVal()();
            var gliderClass = "glider" + id;
            var carousel = $doc.createElement("div");
            carousel.className = "glider-contain multiple";
            carousel.id = "glider" + id;
            carousel.innerHTML = "<div class=\"" + gliderClass + "\">" +
                "</div>" +
                "<button aria-label=\"Previous\" class=\"glider-prev\">«</button>" +
                "<button aria-label=\"Next\" class=\"glider-next\">»</button>" +
                "<div role=\"tablist\" class=\"dots\"></div>";
            whereToInsert.insertAdjacentElement("beforeend", carousel);

            var glider = new $wnd.Glider($doc.querySelector("." + gliderClass), {
                slidesToShow: 5,
                slidesToScroll: 5,
                srollLock: true,
                arrows: {
                    prev: '.glider-prev',
                    next: '.glider-next'
                }
            });
            var that = this;
            that.glider = glider;
            that.gliderElem = carousel;
        }
    }-*/;

    public native void addItem(Element itemToAdd) /*-{
        var that = this;
        itemToAdd.addEventListener("click", function () {
            that.@GliderWrapper::posterClick()();
        });
        that.glider.addItem(itemToAdd);

    }-*/;

    public native Element getGliderElem() /*-{
        var that = this;
        return that.gliderElem;
    }-*/;

    public native Object getGlider() /*-{
        var that = this;
        return that.glider;
    }-*/;

    private void posterClick() {
        History.newItem(Pages.DETAILS_MOVIE.getStrValue());
        RootPanel.get("content").clear();
        RootPanel.get("content").add(new MoviePage());
    }
}
