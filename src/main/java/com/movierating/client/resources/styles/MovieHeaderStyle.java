package com.movierating.client.resources.styles;

import com.google.gwt.resources.client.CssResource;

public interface MovieHeaderStyle extends CssResource {
    @ClassName("div-header")
    String divHeader();

    String viewAllButton();

    String h2();

    @ClassName("releases-header")
    String releasesHeader();
}
