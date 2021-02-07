package com.movierating.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * @param bi
     * @param format of file (jpg,png, etc...)
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(BufferedImage bi, String format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, format, baos);
        } catch (IOException e) {
            log.warn("Image write problem, message = " + e.getMessage());
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static BufferedImage toBufferedImage(byte[] bytes) {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(is);
        } catch (IOException e) {
            log.warn("Image read problem, message = " + e.getMessage());
        }
        return bi;

    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_BGR);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static byte[] getResourceImgBytes(String imgPath, Object obj) {
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage;
        byte[] imgBytes = null;
        try {
            bufImage = ImageIO.read(is);
            imgBytes = ImageUtils.toByteArray(bufImage, "jpg");
        } catch (IOException e) {
            log.warn("Image read problem, message = " + e.getMessage());
        }
        return imgBytes;
    }

    public static byte[] getResourceImgBytes(String imgPath, Object obj, int width, int height) {
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage;
        byte[] imgBytes = null;
        try {
            bufImage = ImageIO.read(is);
            bufImage = ImageUtils.resize(bufImage, width, height);
            imgBytes = ImageUtils.toByteArray(bufImage, "jpg");
        } catch (IOException e) {
            log.warn("Image read problem, message = " + e.getMessage());
        }
        return imgBytes;
    }

    public static BufferedImage getResourceImgBuf(String imgPath, Object obj, int width, int height) {
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage = null;
        try {
            bufImage = ImageIO.read(is);
            bufImage = ImageUtils.resize(bufImage, width, height);
        } catch (IOException e) {
            log.warn("Image read problem, message = " + e.getMessage());
        }
        return bufImage;
    }

    public static BufferedImage getResourceImgBuf(String imgPath, Object obj) {
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage = null;
        try {
            bufImage = ImageIO.read(is);
        } catch (IOException e) {
            log.warn("Image read problem, message = " + e.getMessage());
        }
        return bufImage;
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
