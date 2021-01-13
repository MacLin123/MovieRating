package com.movierating.client.utils;

import com.github.nmorel.gwtjackson.client.utils.Base64Utils;

public class ImageUtils {
    public static final String additionStr = "data:image/jpg;base64,";

    public static String getImageData(byte[] img) {
        String base64 = Base64Utils.toBase64(img);
        base64 = additionStr + base64;
        return base64;
    }

    public static byte[] getBytesImage(String url) {
        String base64 = url.substring(additionStr.length());
        byte[] imgBytes = Base64Utils.fromBase64(base64);
        return imgBytes;
    }
}
