package io.keepcoding.blockbusterrealm.ui.movie.view;

import io.keepcoding.blockbusterrealm.domain.business.Movie;

public interface DetailView {

    void renderMovie(Movie movie);

    void showError();

    void showEmptyCase();

    void hideEmptyCase();

    void hideProgressBar();

    void showProgressBar();
}
