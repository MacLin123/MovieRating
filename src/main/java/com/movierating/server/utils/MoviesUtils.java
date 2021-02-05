package com.movierating.server.utils;

import com.movierating.server.config.ConfigMovie;
import com.movierating.server.model.Movie;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;

public class MoviesUtils {
    public static Movie createMovie(byte[] imgLargeBytes, String title, String description, String genre, Date date, String youtubeId) {
        if (imgLargeBytes.length == 0) {
            return new Movie(title, description, genre, date, youtubeId);
        }
        HashMap<String, byte[]> imgBytes = new HashMap<>();
        BufferedImage img1 = ImageUtils.toBufferedImage(imgLargeBytes);

        imgBytes.put(ConfigMovie.IMG_COVER_KEY.getText(),
                ImageUtils.toByteArray(ImageUtils.resize(img1,
                        ConfigMovie.IMG_COVER_WIDTH.getValue(),
                        ConfigMovie.IMG_COVER_HEIGHT.getValue()),
                        ConfigMovie.DEFAULT_IMAGE_FORMAT.getText()));

        imgBytes.put(ConfigMovie.IMG_MEDIUM_KEY.getText(),
                ImageUtils.toByteArray(ImageUtils.resize(img1,
                        ConfigMovie.IMG_MEDIUM_WIDTH.getValue(),
                        ConfigMovie.IMG_MEDIUM_HEIGHT.getValue()),
                        ConfigMovie.DEFAULT_IMAGE_FORMAT.getText()));
        imgBytes.put(ConfigMovie.IMG_LARGE_KEY.getText(), imgLargeBytes);

        return new Movie(title, description, genre, date,
                youtubeId, imgBytes);
    }
}
