package com.movierating.client.widgets;

public class Sequence {
    private static int nextVal = 0;

    public static int getNextVal() {
        return nextVal++;
    }
}
