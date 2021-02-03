//package com.movierating.client.ui.home;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Image;
//import com.movierating.client.resources.Resources;
//
//import java.util.ArrayList;
//
//public class CarouselWidget extends HorizontalPanel {
////    interface CarouselWidgetUiBinder extends UiBinder<HTMLPanel, CarouselWidget> {
////    }
//
////    private static CarouselWidgetUiBinder ourUiBinder = GWT.create(CarouselWidgetUiBinder.class);
//
//    private ArrayList<Image> images = new ArrayList<>();
//    private Button leftBtn;
//    private Button rightBtn;
//    private static Resources resources = GWT.create(Resources.class);
//    private int curImgIndexWidget = 0;
//    private int startIndexWidget = 1;
//
//    public CarouselWidget() {
//        leftBtn = new Button("left");
//        rightBtn = new Button("right");
//        insert(leftBtn, 0);
////        images.add(new Image(resources.emptyCover()));
////        images.add(new Image(resources.emptyCover()));
////        images.add(new Image(resources.emptyCover()));
////        insert(images.get(0), getNextImgIndex());
////        insert(images.get(1), 1);
////        insert(images.get(2), 1);
//
//        insert(rightBtn, getWidgetCount());
////        initWidget(ourUiBinder.createAndBindUi(this));
//        reset();
//    }
//
//    private int getNextImgIndex() {
//        return curImgIndexWidget + 1;
//    }
//    private void reset() {
//        clear();
//        insert(leftBtn, 0);
//        insert(rightBtn, getWidgetCount());
//    }
//}