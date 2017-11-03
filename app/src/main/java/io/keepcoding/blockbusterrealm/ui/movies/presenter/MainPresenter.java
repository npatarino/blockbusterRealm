package io.keepcoding.blockbusterrealm.ui.movies.presenter;

import android.util.Log;
import com.idealista.android.elves.usecase.UiCommand;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.usecases.ListMovies;
import io.keepcoding.blockbusterrealm.ui.movies.view.MainView;
import org.jetbrains.annotations.NotNull;

public class MainPresenter {

    private final MainView view;
    private final ListMovies listMovies;

    public MainPresenter(final MainView view, @NotNull final ListMovies listMovies) {
        this.view = view;
        this.listMovies = listMovies;
    }

    public void start() {
        loadMovies();
    }

    public void onMovieClicked(final Movie movie) {
        view.goToDetail(movie);
    }

    public void reload() {
        loadMovies();
    }

    private void loadMovies() {
        view.clearMovies();
        view.showProgressBar();
        listMovies.execute(new UiCommand<Movies, Movies>() {
            @Override public Movies background(final Movies movies) {
                return movies;
            }

            @Override public void ui(final Movies movies) {
                if (movies.isEmpty()) {
                    view.showEmptyCase();
                } else {
                    view.hideEmptyCase();
                    view.render(movies);
                }
                view.hideProgressBar();
            }

            @Override public void error(final Exception error) {
                Log.d("MainPresenter", error.getLocalizedMessage());
                view.showError();
            }
        });
    }
}
