package com.movierating.client.config;

public enum Pages {
    CREATE_MOVIE("create-movie"),
    UPDATE_MOVIE("update-movie"),
    DETAILS_MOVIE("details-movie"),
    ADMIN("admin"),
    HOME("home");
    private String strValue;

    Pages(String strValue) {
        this.strValue = strValue;
    }

    public String getStrValue() {
        return strValue;
    }

}
