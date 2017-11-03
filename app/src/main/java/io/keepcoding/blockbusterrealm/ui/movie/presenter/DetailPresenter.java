package io.keepcoding.blockbusterrealm.ui.movie.presenter;

import com.idealista.android.elves.usecase.UiCommand;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.usecases.GetMovie;
import io.keepcoding.blockbusterrealm.ui.movie.view.DetailActivity;
import io.keepcoding.blockbusterrealm.ui.movie.view.DetailView;

public class DetailPresenter {

    private final DetailView view;
    private final GetMovie getMovieUseCase;

    public DetailPresenter(final DetailView view, final GetMovie getMovieUseCase) {
        this.view = view;
        this.getMovieUseCase = getMovieUseCase;
    }

    public void load() {
        getMovieUseCase.execute(new UiCommand<Movie, Movie>() {
            @Override public Movie background(final Movie movie) {
                return movie;
            }

            @Override public void ui(final Movie movie) {
                view.hideProgressBar();
                view.renderMovie(movie);
            }

            @Override public void error(final Exception error) {
                view.showError();
            }
        });
    }
}
