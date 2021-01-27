package com.movierating.client.widgets;

import com.google.gwt.dom.client.Element;

public class GliderWrapper {
    private Object glider;
    private Element gliderElem;

    public GliderWrapper() {

    }

    public native void createGlider(Element whereToInsert) /*-{
        if ($doc.readyState != "loading") {

            var carousel = $doc.createElement("div");
            carousel.className = "glider-contain multiple";
            carousel.id = "glider" + @Sequence::getNextVal()();
            carousel.innerHTML = "<div class=\"glider\">" +
                "</div>" +
                "<button aria-label=\"Previous\" class=\"glider-prev\">«</button>" +
                "<button aria-label=\"Next\" class=\"glider-next\">»</button>" +
                "<div role=\"tablist\" class=\"dots\"></div>";
            whereToInsert.insertAdjacentElement("beforeend", carousel);

            var glider = new $wnd.Glider($doc.querySelector('.glider'), {
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

}
