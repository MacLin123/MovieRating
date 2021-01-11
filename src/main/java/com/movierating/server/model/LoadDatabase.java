package com.movierating.server.model;

import com.movierating.server.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final String sDate = "01/12/2020";
    private final String endDate = "31/12/2020";
    private Date dateStart = null;
    private Date dateEnd = null;

    @Bean
    CommandLineRunner initMovies(MovieRepository repository) {
        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date1 = sFormat.parse("1941-09-04");
            date2 = sFormat.parse("1972-03-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String imgPath = "static/imgs/no_img.jpg";
        InputStream is = getClass().getClassLoader().getResourceAsStream(imgPath);

        BufferedImage bufImage;
        byte[] imgBytes = null;
        try {
            bufImage = ImageIO.read(is);
            bufImage = ImageUtils.resize(bufImage,80,120);
            imgBytes = ImageUtils.toByteArray(bufImage,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Movie movie1 = new Movie("Citizen Kane", "good film", "Drama", date1, imgBytes);
        Movie movie2 = new Movie("The Godfather", "good film", "Drama", date2, imgBytes);

        return args -> {
            log.info("Preloading " + repository.save(movie1));
            log.info("Preloading " + repository.save(movie2));
        };
    }
}