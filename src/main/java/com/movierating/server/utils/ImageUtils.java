package com.movierating.server.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    //    public static BufferedImage toBufferedImage(byte[] bytes)
//            throws IOException {
//
//        InputStream is = new ByteArrayInputStream(bytes);
//        BufferedImage bi = ImageIO.read(is);
//        return bi;
//
//    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_BGR);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static byte[] getResourceImg(String imgPath, Object obj) {
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage;
        byte[] imgBytes = null;
        try {
            bufImage = ImageIO.read(is);
            bufImage = ImageUtils.resize(bufImage,80,120);
            imgBytes = ImageUtils.toByteArray(bufImage,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  imgBytes;
    }
}
