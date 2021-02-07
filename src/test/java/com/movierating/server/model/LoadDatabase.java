package com.movierating.server.model;

import com.movierating.server.config.ConfigMovie;
import com.movierating.server.repository.MovieRepository;
import com.movierating.server.utils.DateUtils;
import com.movierating.server.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initMovies(MovieRepository repository) {

        ArrayList<Movie> movies = new ArrayList<>();

        Date date1 = DateUtils.convertStringToDate("1941-09-04");
        String imgPath1 = "static/imgs/citizen_kane.jpg";
        String description1 = "When a reporter is assigned to decipher newspaper magnate Charles Foster Kane's (Orson Welles) dying words, his investigation gradually reveals the fascinating portrait of a complex man who rose from obscurity to staggering heights. Though Kane's friend and colleague Jedediah Leland (Joseph Cotten), and his mistress, Susan Alexander (Dorothy Comingore), shed fragments of light on Kane's life, the reporter fears he may never penetrate the mystery of the elusive man's final word, \"Rosebud.\"";
        movies.add(createMovie(imgPath1, "Citizen Kane", description1, "Drama", date1,
                "8dxh3lwdOFw"));

        Date date2 = DateUtils.convertStringToDate("1972-03-11");
        String imgPath2 = "static/imgs/god_father.jpg";
        String description2 = "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.";
        movies.add(createMovie(imgPath2, "The Godfather", description2, "Drama",
                date2, "sY1S34973zA"));

        Date date3 = DateUtils.convertStringToDate("2015-07-01");
        String imgPath3 = "static/imgs/terminator_genesis.jpg";
        String description3 = "When John Connor, leader of the human resistance, sends Sgt. Kyle Reese back to 1984 to protect Sarah Connor and safeguard the future, an unexpected turn of events creates a fractured timeline.";
        movies.add(createMovie(imgPath3, "Terminator Genisys", description3, "Action",
                date3, "FqbOFjl7ZWE"));

        //new releases
        Date date4 = DateUtils.convertStringToDate("2020-12-25");
        String imgPath4 = "static/imgs/ww1984.jpg";
        String description4 = "Diana Prince lives quietly among mortals in the vibrant, sleek 1980s -- an era of excess driven by the pursuit of having it all. Though she's come into her full powers, she maintains a low profile by curating ancient artifacts, and only performing heroic acts incognito. But soon, Diana will have to muster all of her strength, wisdom and courage as she finds herself squaring off against Maxwell Lord and the Cheetah, a villainess who possesses superhuman strength and agility.";
        movies.add(createMovie(imgPath4, "Wonder Woman 1984", description4,
                "Action, Fantasy, Adventure", date4, "sfM7_JLk-84"));

        Date date5 = DateUtils.convertStringToDate("2020-12-18");
        String imgPath5 = "static/imgs/monster_hunter.jpg";
        String description5 = "Behind our world, there is another -- a world of dangerous and powerful monsters that rule their domain with deadly ferocity. When Lt. Artemis and her loyal soldiers are transported from our world to the new one, the unflappable lieutenant receives the shock of her life. In a desperate battle for survival against enormous enemies with incredible powers and unstoppable, terrifying attacks, Artemis teams up with a mysterious hunter who has found a way to fight back.";
        movies.add(createMovie(imgPath5, "Monster Hunter", description5,
                "Adventure, Action, Fantasy", date5, "3od-kQMTZ9M"));

        Date date6 = DateUtils.convertStringToDate("2020-12-18");
        String imgPath6 = "static/imgs/skyline.jpg";
        String description6 = "When a virus threatens to turn friendly alien hybrids against humans, Capt. Rose Corley and her team of elite soldiers embark on a mission to an extraterrestrial world to save what's left of mankind.";
        movies.add(createMovie(imgPath6, "Skylines", description6,
                "Mystery And Thriller, Action, Sci Fi", date6, "mYxIu5Z0BO8"));

        Date date7 = DateUtils.convertStringToDate("2020-01-10");
        String imgPath7 = "static/imgs/1917.jpg";
        String description7 = "During World War I, two British soldiers -- Lance Cpl. Schofield and Lance Cpl. Blake -- receive seemingly impossible orders. In a race against time, they must cross over into enemy territory to deliver a message that could potentially save 1,600 of their fellow comrades -- including Blake's own brother.";
        movies.add(createMovie(imgPath7, "1917", description7,
                "War, Drama, History", date7, "gZjQROMAh_s"));

        Date date8 = DateUtils.convertStringToDate("2020-12-25");
        String imgPath8 = "static/imgs/soul.jpg";
        String description8 = "Joe is a middle-school band teacher whose life hasn't quite gone the way he expected. His true passion is jazz -- and he's good. But when he travels to another realm to help someone find their passion, he soon discovers what it means to have soul.";
        movies.add(createMovie(imgPath8, "Soul", description8,
                "Comedy, Kids, Family, Animation, Adventure", date8, "xOsLIiBStEs"));


        Date date9 = DateUtils.convertStringToDate("2020-07-30");
        String imgPath9 = "static/imgs/host.jpg";
        String description9 = "Six friends accidentally invite the attention of a demonic presence during an online séance and begin noticing strange occurrences in their homes.";
        movies.add(createMovie(imgPath9, "Host", description9,
                "Horror, Mystery, Thriller", date9, "SNlKbqHqGcY"));

        Date date10 = DateUtils.convertStringToDate("2020-03-17");
        String imgPath10 = "static/imgs/blow_the_man_down.jpg";
        String description10 = "Sisters have to cover up a crime while grieving their father's death.";
        movies.add(createMovie(imgPath10, "Blow The Man Down", description10,
                "Drama, Comedy", date10, "uWM1U_kd0rE"));

        Date date11 = DateUtils.convertStringToDate("2020-09-18");
        String imgPath11 = "static/imgs/rocks.jpg";
        String description11 = "A London teen takes care of her younger brother after their mother abruptly leaves.";
        movies.add(createMovie(imgPath11, "Rocks", description11,
                "Drama", date11, "NULP0s2FhPE"));

        Date date12 = DateUtils.convertStringToDate("2020-02-28");
        String imgPath12 = "static/imgs/the_invisible_man.jpg";
        String description12 = "After staging his own suicide, a crazed scientist uses his power to become invisible to stalk and terrorize his ex-girlfriend. When the police refuse to believe her story, she decides to take matters into her own hands and fight back.";
        movies.add(createMovie(imgPath12, "The Invisible Man", description12,
                "Mystery, Thriller, Horror", date12, "WO_FJdiY9dA"));

        //upcoming movies
        Date date13 = DateUtils.convertStringToDate("2021-12-22");
        String imgPath13 = "static/imgs/the_nightingale.jpg";
        String description13 = "The lives of two sisters living in France are torn apart at the onset of World War II. Based on Kristin Hannah's novel 'The Nightingale'.";
        movies.add(createMovie(imgPath13, "The Nightingale", description13,
                "Drama, History, War", date13, "YuP8g_GQIgI"));

        Date date14 = DateUtils.convertStringToDate("2022-01-15");
        String imgPath14 = "static/imgs/the_355.jpg";
        String description14 = "Five women band together to stop a global organization from acquiring a weapon that could thrust the teetering world into total chaos.";
        movies.add(createMovie(imgPath14, "The 355", description14,
                "Mystery, Thriller, Action", date14, "Kbx9I-R8xVQ"));

        Date date15 = DateUtils.convertStringToDate("2021-12-10");
        String imgPath15 = "static/imgs/west_side_story.jpg";
        String description15 = "Two teenagers from different ethnic backgrounds fall in love in 1950s New York City.";
        movies.add(createMovie(imgPath15, "West Side Story", description15,
                "Musical", date15, "NF1L3NorO3E"));

        Date date16 = DateUtils.convertStringToDate("2021-12-22");
        String imgPath16 = "static/imgs/the_matrix_4.jpg";
        String description16 = "The further adventures of Neo and Trinity.";
        movies.add(createMovie(imgPath16, "The Matrix 4", description16,
                "Sci Fi, Action", date16, "L2ItYEV2QFk"));

        Date date17 = DateUtils.convertStringToDate("2021-12-22");
        String imgPath17 = "static/imgs/sing2.jpg";
        String description17 = "Buster Moon and his friends must persuade reclusive rock star Clay Calloway to join them for the opening of a new show.";
        movies.add(createMovie(imgPath17, "Sing 2", description17,
                "Comedy, Animation, Kids And Family, Musical", date17,"oBoEV5awLsA"));

        Date date18 = DateUtils.convertStringToDate("2022-01-14");
        String imgPath18 = "static/imgs/scream5.jpg";
        String description18 = "A new installment of the 'Scream' horror franchise will follow a woman returning to her home town to try to find out who has been committing a series of vicious crimes.";
        movies.add(createMovie(imgPath18, "Scream 5", description18,
                "Mystery, Thriller, Horror", date18,"ktTFGB9CDe0"));

        Date date19 = DateUtils.convertStringToDate("2021-12-22");
        String imgPath19 = "static/imgs/sherlock_holmes3.jpg";
        String description19 = "The further adventures of Detective Sherlock Holmes and Dr. John Watson.";
        movies.add(createMovie(imgPath19, "Sherlock Holmes 3", description19,
                "Mystery, Thriller, Adventure", date19,"s1s-XbvncNg"));

        //2021 movies that has released

        Date date20 = DateUtils.convertStringToDate("2021-01-01");
        String imgPath20 = "static/imgs/breach.jpg";
        String description20 = "Fleeing a devastating plague on Earth, an interstellar ark comes under attack from a new threat -- a shape-shifting alien force intent on slaughtering what's left of humanity.";
        movies.add(createMovie(imgPath20, "Breach", description20,
                "Action, Sci Fi", date20,"gerLMlPWwQ0"));

        Date date21 = DateUtils.convertStringToDate("2021-01-04");
        String imgPath21 = "static/imgs/castle_in_the_ground.jpg";
        String description21 = "A grieving teen befriends his charismatic but troubled neighbor, only to become ensnared in a world of addiction and violence as the opioid epidemic takes hold of his small town.";
        movies.add(createMovie(imgPath21, "Castle In The Ground", description21,
                "Drama", date21,"Zsq0X9DNzt4"));

        Date date22 = DateUtils.convertStringToDate("2021-01-07");
        String imgPath22 = "static/imgs/the_very_excellent_mr_dundee.jpg";
        String description22 = "Paul Hogan is reluctantly thrust back into the spotlight as he desperately attempts to restore his sullied reputation on the eve of being knighted.";
        movies.add(createMovie(imgPath22, "The Very Excellent Mr. Dundee", description22,
                "Comedy", date22,"x61_6wfcrS4"));

        Date date23 = DateUtils.convertStringToDate("2021-01-15");
        String imgPath23 = "static/imgs/marksman.jpg";
        String description23 = "A rancher on the Arizona border becomes the unlikely defender of a young Mexican boy desperately fleeing the cartel assassins who've pursued him into the U.S.";
        movies.add(createMovie(imgPath23, "The Marksman", description23,
                "Action, Mystery, Thriller", date23,"EBPNi4bEbc"));

        Date date24 = DateUtils.convertStringToDate("2021-01-29");
        String imgPath24 = "static/imgs/the_little_things.jpg";
        String description24 = "Deputy Sheriff Joe \"Deke\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.";
        movies.add(createMovie(imgPath24, "The Little Things", description24,
                "Mystery, Thriller, Crime, Drama", date24,"1HZAnkxdYuA"));

        Date date25 = DateUtils.convertStringToDate("2021-01-31");
        String imgPath25 = "static/imgs/prisoners_of_the_ghostland.jpg";
        String description25 = "A notorious criminal must break an evil curse in order to rescue an abducted girl who has mysteriously disappeared.";
        movies.add(createMovie(imgPath25, "Prisoners Of The Ghostland", description25,
                "Action, Horror, Mystery And Thriller", date25,"66JuZ1cb03E"));

        // 2020
        Date date26 = DateUtils.convertStringToDate("2020-09-04");
        String imgPath26 = "static/imgs/mulan.jpg";
        String description26 = "To save her ailing father from serving in the Imperial Army, a fearless young woman disguises herself as a man to battle northern invaders in China.";
        movies.add(createMovie(imgPath26, "Mulan", description26,
                "Action, Adventure", date26,"KK8FHdFluOQ"));

        return args -> {
            loadToDBMovie(repository, movies);
        };
    }

    private void loadToDBMovie(MovieRepository repository, ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            log.info("Preloading " + repository.save(movie));
        }
    }

    private Movie createMovie(String imgPath, String title, String description, String genre, Date date, String youtubeId) {
        HashMap<String, byte[]> imgBytes = new HashMap<>();
        BufferedImage img1 = ImageUtils.getResourceImgBuf(imgPath, this);

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

        imgBytes.put(ConfigMovie.IMG_LARGE_KEY.getText(), ImageUtils.toByteArray(img1,
                ConfigMovie.DEFAULT_IMAGE_FORMAT.getText()));

        return new Movie(title, description, genre, date,
                youtubeId, imgBytes);
    }
}