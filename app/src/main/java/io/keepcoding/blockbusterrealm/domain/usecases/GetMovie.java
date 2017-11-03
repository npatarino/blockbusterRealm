package io.keepcoding.blockbusterrealm.domain.usecases;

import com.idealista.android.elves.usecase.UseCase;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.repositories.MoviesRepository;
import java.util.concurrent.Callable;

public class GetMovie extends UseCase<Movie, Movie> {

    private final String id;
    private final MoviesRepository repository;

    public GetMovie(final String id, final MoviesRepository repository) {
        this.id = id;
        this.repository = repository;
    }

    @Override protected Callable<Movie> build() {
        return new Callable<Movie>() {
            @Override public Movie call() throws Exception {
                return repository.getById(id);
            }
        };
    }
}
