package com.movierating.client.config;

public enum FileConfig {
    MAX_FILE_SIZE(5 * 1024 * 1024);
    private int value;

    FileConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
