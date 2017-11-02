package io.keepcoding.blockbusterrealm.ui.movies.view;

import io.keepcoding.blockbusterrealm.domain.business.Movies;

public interface MainView {

    void render(Movies movies);

    void showError();

    void showEmptyCase();

    void hideEmptyCase();

    void hideProgressBar();

    void showProgressBar();

    void clearMovies();
}
