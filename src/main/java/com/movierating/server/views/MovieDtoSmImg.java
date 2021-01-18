package com.movierating.server.views;

import java.util.Date;

public interface MovieDtoSmImg {
    Long getId();
    String getTitle();

    String getGenre();
    Date getPremier_Date();
    byte[] getCover_Img();
}
