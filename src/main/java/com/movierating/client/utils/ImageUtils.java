package com.movierating.client.utils;

import com.github.nmorel.gwtjackson.client.utils.Base64Utils;

public class ImageUtils {
    public static String getImageData(byte[] img){
        String base64 = Base64Utils.toBase64(img);
        base64 = "data:image/jpg;base64,"+base64;
        return base64;
    }
}
