package com.movierating.server.config;
public enum Config {
    MAX_FILE_SIZE(5 * 1024 * 1024);
    private int value;

    Config(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
