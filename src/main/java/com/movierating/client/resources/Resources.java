package com.movierating.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
    Resources INSTANCE = GWT.create(Resources.class);
    @Source("staticfiles/images/no_img.jpg")
    ImageResource emptyCover();

    @Source("staticfiles/Glider/glider.js")
    TextResource gliderJs();

    @Source("staticfiles/Glider/glider.min.css")
    TextResource gliderCss();

    @Source("staticfiles/js/youtubePlayer.js")
    TextResource youtubePlayer();


}
