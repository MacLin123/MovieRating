package com.movierating.server.model;

import com.movierating.server.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        Date date3 = null;
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sFormat.parse("1941-09-04");
            date2 = sFormat.parse("1972-03-11");
            date2 = sFormat.parse("2015-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String imgPath1 = "static/imgs/no_img.jpg";
        String imgPath2 = "static/imgs/citizen_kane.jpg";
        String imgPath3 = "static/imgs/god_father.jpg";
        String imgPath4 = "static/imgs/terminator_genesis.jpg";
//        byte[] imgBytes1 = ImageUtils.getResourceImg(imgPath1, this);
        byte[] imgBytes2 = ImageUtils.getResourceImg(imgPath2, this);
        byte[] imgBytes3 = ImageUtils.getResourceImg(imgPath3, this);
        byte[] imgBytes4 = ImageUtils.getResourceImg(imgPath4, this);
//        InputStream is = getClass().getClassLoader().getResourceAsStream(imgPath);
//
//        BufferedImage bufImage;
//        byte[] imgBytes = null;
//        try {
//            bufImage = ImageIO.read(is);
//            bufImage = ImageUtils.resize(bufImage,80,120);
//            imgBytes = ImageUtils.toByteArray(bufImage,"jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Movie movie1 = new Movie("Citizen Kane", "good film", "Drama", date1, imgBytes2);
        Movie movie2 = new Movie("The Godfather", "good film", "Drama", date2, imgBytes3);
        Movie movie3 = new Movie("Terminator Genisys", "Arnold...", "Action", date2, imgBytes4);

        return args -> {
            log.info("Preloading " + repository.save(movie1));
            log.info("Preloading " + repository.save(movie2));
            log.info("Preloading " + repository.save(movie3));
        };
    }
}