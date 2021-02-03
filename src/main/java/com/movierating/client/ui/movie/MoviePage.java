package com.movierating.client.ui.movie;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.controller.MovieService;
import com.movierating.client.model.Movie;
import com.movierating.client.resources.Resources;
import com.movierating.client.resources.styles.MovieScoreStyle;
import com.movierating.client.ui.home.PosterMovieList;
import com.movierating.client.utils.ImageUtils;
import com.movierating.client.utils.ScoreUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class MoviePage extends Composite {
    interface MoviePageUiBinder extends UiBinder<HTMLPanel, MoviePage> {
    }

    private static MoviePageUiBinder ourUiBinder = GWT.create(MoviePageUiBinder.class);

    private static final MovieService movieService = GWT.create(MovieService.class);

    public class SameGenreLazyPanel extends LazyPanel {

        @Override
        protected Widget createWidget() {
            return new PosterMovieList(movie.getGenre());
        }
    }

    @UiField
    Image coverMedium;

    @UiField
    MovieScoreStyle movieScoreStyle;

    @UiField
    DivElement ratingDiv;

    @UiField
    SpanElement titleElem;

    @UiField
    SpanElement yearElem;

    @UiField
    SpanElement genreElem;

    @UiField
    SpanElement premierDateElem;
    @UiField
    Label descrLabel;

    private Movie movie;
    //    @UiField(provided = true)
//    PosterMovieList sameGenreList;
    @UiField(provided = true)
    LazyPanel sameGenrePanel;

    String videoId;

    public MoviePage(Long movieId) {

        sameGenrePanel = new SameGenreLazyPanel();
        sameGenrePanel.setVisible(false);
        initWidget(ourUiBinder.createAndBindUi(this));
        getMovieDetails(movieId);

        videoId = "8dxh3lwdOFw";

//        ScriptInjector.fromString(Resources.INSTANCE.youtubePlayer().getText())
//                .setWindow(ScriptInjector.TOP_WINDOW)
//                .setRemoveTag(false)
//                .inject();
//        ScriptInjector.fromUrl("https://www.youtube.com/iframe_api")
//                .setWindow(ScriptInjector.TOP_WINDOW)
//                .setRemoveTag(false)
//                .inject();
//        func1();
//        attachYoutubePlayer();
//        funcinit();

    }

    private native void funcinit() /*-{
//        var youtubePlayer = new $wnd.YoutubePlayer('M7lc1UVf-VE');
//        youtubePlayer.runScript()
        var player = $wnd.player;
        console.log(player);
        player.stopVideo();
    }-*/;

    private native void attachYoutubePlayer() /*-{
        // 2. This code loads the IFrame Player API code asynchronously.
        var tag = $doc.createElement('script');
        tag.src = "https://www.youtube.com/iframe_api";
        var firstScriptTag = $doc.getElementsByTagName('script');
        firstScriptTag = firstScriptTag[firstScriptTag.length - 1];

        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
        var player;

        function onYouTubeIframeAPIReady() {
            player = new YT.Player('player_youtube', {
                height: '360',
                width: '640',
                videoId: 'M7lc1UVf-VE',
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
        }

// 4. The API will call this function when the video player is ready.
        function onPlayerReady(event) {
            event.target.playVideo();
        }

// 5. The API calls this function when the player's state changes.
//    The function indicates that when playing a video (state=1),
//    the player should play for six seconds and then stop.
        var done = false;

        function onPlayerStateChange(event) {
            if (event.data == YT.PlayerState.PLAYING && !done) {
                setTimeout(stopVideo, 6000);
                done = true;
            }
        }

        function stopVideo() {
            player.stopVideo();
        }
    }-*/;

    private native void runYt() /*-{
//        var youtubePlayer = new $wnd.YoutubePlayer('M7lc1UVf-VE');
//        youtubePlayer.runScript()
        $wnd.runScript();
    }-*/;

    private void getMovieDetails(Long movieId) {
        movieService.getMovieDetails(movieId, new MethodCallback<Movie>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                GWT.log(method.getResponse().getText(), exception);
            }

            @Override
            public void onSuccess(Method method, Movie respMovie) {
                movie = respMovie;

                coverMedium.setUrl(ImageUtils.getImageData(movie.getCoverImg()));
                if (movie.getRating() != null) {
                    ScoreUtils.initRatingMarkStyle(movie.getRating(), ratingDiv, movieScoreStyle);
                    ratingDiv.setInnerText(String.valueOf(movie.getRating()));
                } else {
                    ratingDiv.setInnerText("No");
                    ratingDiv.addClassName(movieScoreStyle.neutral());
                }

                titleElem.setInnerText(movie.getTitle());
                yearElem.setInnerText("(" + DateTimeFormat.getFormat("yyyy").format(movie.getPremierDate()) + ")");
                premierDateElem.setInnerText(movie.getPremierDateString());
                genreElem.setInnerText(movie.getGenre());
                descrLabel.setText(movie.getDescription());

                sameGenrePanel.setVisible(true); // start load sameGenreWidget

                initVideo();
            }
        });
    }

    private void initVideo() {
        ScriptInjector.fromString(Resources.INSTANCE.youtubePlayer().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setRemoveTag(false)
                .inject();
        Element playerDiv = Document.get().getElementById("player_youtube");
        playerDiv.setTitle(videoId);
    }
}