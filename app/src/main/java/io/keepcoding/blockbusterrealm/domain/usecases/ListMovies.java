package io.keepcoding.blockbusterrealm.domain.usecases;

import com.idealista.android.elves.usecase.UseCase;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.repositories.MoviesRepository;
import java.util.concurrent.Callable;

public class ListMovies extends UseCase<Movies, Movies> {

    private final MoviesRepository repository;

    public ListMovies(final MoviesRepository repository) {this.repository = repository;}

    @Override protected Callable<Movies> build() {
        return new Callable<Movies>() {
            @Override public Movies call() throws Exception {
                return repository.getAll();
            }
        };
    }
}
