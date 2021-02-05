package com.movierating.client.ui.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import com.movierating.client.config.Pages;
import com.movierating.client.config.SearchPanelConfig;
import com.movierating.client.controller.AdminService;
import com.movierating.client.controller.MovieService;
import com.movierating.client.model.Movie;
import com.movierating.client.ui.movie.MovieFormPanel;
import com.movierating.client.ui.movie.MovieLabel;
import com.movierating.client.ui.movie.MoviePage;
import com.movierating.client.utils.ImageUtils;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class SearchPanel extends Composite {
    interface SearchPanelUiBinder extends UiBinder<HTMLPanel, SearchPanel> {
    }

    interface MyStyle extends CssResource {

        String center();

        String header();

        String flex_table();

        @ClassName("input-group-search")
        String inputGroupSearch();

        @ClassName("input-group-button")
        String inputGroupButton();

        @ClassName("input-group-search-text")
        String inputGroupSearchText();

        @ClassName("input-group-search-button")
        String inputGroupSearchButton();

        @ClassName("search-panel")
        String searchPanel();

        @ClassName("img-search-panel")
        String imgSearchPanel();
    }

    private static SearchPanelUiBinder ourUiBinder = GWT.create(SearchPanelUiBinder.class);

    private static final AdminService adminService = GWT.create(AdminService.class);

    private static final MovieService movieService = GWT.create(MovieService.class);

    @UiField
    FlexTable movieList;

    @UiField
    TextBox searchByTitleTextBox;

    @UiField
    Button searchMovieBtn;

    @UiField
    MyStyle style;
    @UiField
    HeadingElement pageHeader;

    private SearchPanelConfig panelType;


    public SearchPanel(SearchPanelConfig panelType) {
        this.panelType = panelType;
        initWidget(ourUiBinder.createAndBindUi(this));

        if (panelType.equals(SearchPanelConfig.ADMIN_SEARCH)) {
            pageHeader.setInnerText("Admin Page");
            searchMovieBtn.addClickHandler(event -> {
                refreshAdminMovies();
            });
            searchByTitleTextBox.getElement().setAttribute("placeholder", "title to search");
        } else if (panelType.equals(SearchPanelConfig.USER_NEW_RELEASES)) {
            pageHeader.setInnerText("Search Page"); // maybe indicate "New releases" etc ...
            searchMovieBtn.setVisible(false);
            searchByTitleTextBox.setVisible(false);

            movieService.getAllNewReleases(new SearchPanelCallback());
        } else if (panelType.equals(SearchPanelConfig.USER_UPCOMING_RELEASES)) {
            pageHeader.setInnerText("Search Page"); // maybe indicate "New releases" etc ...
            searchMovieBtn.setVisible(false);
            searchByTitleTextBox.setVisible(false);

            movieService.getAllUpcomingReleases(new SearchPanelCallback());
        }

    }

//    private void refreshNewReleasesMovies() {
//        movieService.getAllNewReleases(new SearchPanelCallback());
//    }


    /**
     * get data from the server and refresh front-end in admin seach
     */
    private void refreshAdminMovies() {
        String searchTitle = searchByTitleTextBox.getText();
        if (searchTitle.isEmpty()) {
            return;
        }
        adminService.searchMovies(searchTitle, new SearchPanelCallback());
    }

    private class SearchPanelCallback implements MethodCallback<List<Movie>> {
        @Override
        public void onFailure(Method method, Throwable exception) {
            GWT.log(method.getResponse().getText(), exception);
        }

        @Override
        public void onSuccess(Method method, List<Movie> response) {
            movieList.removeAllRows();
            for (final Movie movie : response) {
                int row = movieList.getRowCount();

                Image img = new Image(ImageUtils.getImageData(movie.getCoverImg()));
                img.addStyleName(style.imgSearchPanel());

//                img.addClickHandler(event -> {
//                    History.newItem(Pages.UPDATE_MOVIE.getStrValue());
//                    RootPanel.get("content").clear();
//                    RootPanel.get("content").add(new MovieFormPanel(movie.getId(), "Update Movie"));
//
//                });

                initImgClickHandler(img, movie.getId());

                movieList.setWidget(row, 0, img);
                movieList.setWidget(row, 1, new MovieLabel(movie));
            }
        }
    }

    private void initImgClickHandler(Image img, Long movieId) {
        if (panelType.equals(SearchPanelConfig.ADMIN_SEARCH)) {
            img.addClickHandler(event -> {
                History.newItem(Pages.UPDATE_MOVIE.getStrValue());
                RootPanel.get("content").clear();
                RootPanel.get("content").add(new MovieFormPanel(movieId, "Update Movie"));

            });
        } else if (panelType.equals(SearchPanelConfig.USER_NEW_RELEASES) ||
                panelType.equals(SearchPanelConfig.USER_UPCOMING_RELEASES)) {
            img.addClickHandler(event -> {
                History.newItem(Pages.DETAILS_MOVIE.getStrValue());
                RootPanel.get("content").clear();
                RootPanel.get("content").add(new MoviePage(movieId));
            });
        }
    }
}