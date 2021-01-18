//package com.movierating.client.ui.home;
//
//import com.google.gwt.dom.client.Style;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.shared.EventHandler;
//import com.google.gwt.event.shared.HandlerRegistration;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.LayoutPanel;
//import com.google.gwt.user.client.ui.Widget;
//
//import java.util.ArrayList;
//
//public class CarouselPanel extends LayoutPanel {
//
//    private int itsCurrentWidget = 0;
//    private ArrayList<Widget> itsWidgets;
//    private Button itsLeftButton;
//    private Button itsRightButton;
//    private HandlerRegistration itsRightHandler;
//
//    public CarouselPanel() {
//
//    }
//
//
//    private void setCenter(Widget widget, boolean newWidget) {
//        if (widget != null) {
//            if (newWidget)
//                add(widget);
//            setWidgetLeftWidth(widget, 10, Style.Unit.PCT, 80, Style.Unit.PCT);
//            setWidgetTopHeight(widget, 10, Style.Unit.PCT, 80, Style.Unit.PCT);
//            widget.removeStyleName("sideCarousel");
//            widget.setStylePrimaryName("centerCarousel");
//        }
//    }
//
//    private void setRight(Widget widget, boolean newWidget) {
//        if (widget != null) {
//            if (newWidget)
//                add(widget);
//            setWidgetLeftWidth(widget, 50, Style.Unit.PCT, 45, Style.Unit.PCT);
//            setWidgetTopHeight(widget, 20, Style.Unit.PCT, 60, Style.Unit.PCT);
//            widget.removeStyleName("centerCarousel");
//            widget.setStylePrimaryName("sideCarousel");
//            if (itsRightHandler != null)
//                itsRightHandler.removeHandler();
//            itsRightHandler = widget.addDomHandler(new ClickHandler() {
//                public void onClick(final ClickEvent event) {
//                    scrollRight();
//                }
//            }, ClickEvent.getType());
//        }
//    }
//
//    public void scrollRight() {
//        if (itsCurrentWidget >= getWidgetCountInCarousel()-1)
//            return;
//        if (itsCurrentWidget > 0) {
//            Widget hideWidget = getWidgetInCarousel(itsCurrentWidget-1);
//            remove(hideWidget);
//        }
//        Widget leftWidget = getWidgetInCarousel(itsCurrentWidget);
//        Widget centerWidget = getWidgetInCarousel(++itsCurrentWidget);
//        Widget rightWidget = null;
//        if (itsCurrentWidget+1 < getWidgetCountInCarousel()) {
//            rightWidget = getWidgetInCarousel(itsCurrentWidget+1);
//        }
//        setLeft(leftWidget, false);
//        setRight(rightWidget, true);
//        setCenter(centerWidget, false);
//        animate(500);
//    }
//
//    private void setLeft(Widget leftWidget, boolean newWidget) {
//        if (leftWidget != null) {
//            if (newWidget)
//                add(leftWidget);
//            setWidgetLeftWidth(leftWidget, 0, Style.Unit.PCT, 45, Style.Unit.PCT);
//            setWidgetTopHeight(leftWidget, 0, Style.Unit.PCT, 60, Style.Unit.PCT);
//            leftWidget.removeStyleName("centerCarousel");
//            leftWidget.setStylePrimaryName("sideCarousel");
//            if (itsRightHandler != null)
//                itsRightHandler.removeHandler();
//            itsRightHandler = leftWidget.addDomHandler(new ClickHandler() {
//                public void onClick(final ClickEvent event) {
//                    scrollRight();
//                }
//            }, ClickEvent.getType());
//        }
//    }
//
//    private Widget getWidgetInCarousel(int i) {
//        return itsWidgets.get(i);
//    }
//
//    private int getWidgetCountInCarousel() {
//        return itsWidgets.size();
//    }
//}