package com.movierating.client.config;

public enum Pages {
    CREATE_MOVIE("create-movie"),
    UPDATE_MOVIE("update-movie"),
    DETAILS_MOVIE("details-movie"),
    ADMIN_SEARCH_PANEL("admin_search-panel"),
    NEW_SEARCH_PANEL("new_search-panel"),
    UPCOMING_SEARCH_PANEL("upcoming_search-panel"),
    HOME("home");
    private String strValue;

    Pages(String strValue) {
        this.strValue = strValue;
    }

    public String getStrValue() {
        return strValue;
    }

}
