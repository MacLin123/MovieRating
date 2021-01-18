package com.movierating.server.config;

public enum ConfigMovie {
    IMG_COVER_HEIGHT(120),
    IMG_COVER_WIDTH(80),
    IMG_MEDIUM_HEIGHT(240),
    IMG_MEDIUM_WIDTH(160),
    IMG_COVER_KEY("coverImg"),
    IMG_MEDIUM_KEY("mediumImg"),
    IMG_LARGE_KEY("largeImg"),
    DEFAULT_IMAGE_FORMAT("jpg");
    private int value;
    private String text;

    ConfigMovie(int value) {
        this.value = value;
    }
    ConfigMovie(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }
    public String getText() {
        return text;
    }


}
