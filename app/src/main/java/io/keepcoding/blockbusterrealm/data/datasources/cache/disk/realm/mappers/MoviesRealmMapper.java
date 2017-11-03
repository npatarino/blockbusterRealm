package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.mappers;

import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MovieRealm;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MoviesRealm;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.realm.RealmList;
import java.util.Iterator;

public class MoviesRealmMapper {

    public static final String DEFAULT_KEY = "UNIQUE";

    public Movie map(MovieRealm movieRealm) {

        return new Movie(movieRealm.getId(),
                         movieRealm.getTitle(),
                         movieRealm.getTitleLong(),
                         movieRealm.getCoverSmallUrl(),
                         movieRealm.getCoverMediumUrl(),
                         movieRealm.getCoverLargeUrl(),
                         movieRealm.getDescription());
    }

    public MovieRealm map(Movie movie) {
        final MovieRealm movieRealm = new MovieRealm();
        movieRealm.setId(movie.getId());
        movieRealm.setTitle(movie.getTitle());
        movieRealm.setTitleLong(movie.getTitleLong());
        movieRealm.setCoverSmallUrl(movie.getCoverSmallUrl());
        movieRealm.setCoverMediumUrl(movie.getCoverMediumUrl());
        movieRealm.setCoverLargeUrl(movie.getCoverLargeUrl());
        movieRealm.setDescription(movie.getDescription());
        return movieRealm;
    }

    public MoviesRealm map(Movies movies, Long timestamp) {
        RealmList<MovieRealm> moviesList = new RealmList<>();
        final Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            final Movie movie = iterator.next();
            final MovieRealm movieRealm = map(movie);
            moviesList.add(movieRealm);
        }

        final MoviesRealm moviesRealm = new MoviesRealm();
        moviesRealm.setMovies(moviesList);
        moviesRealm.setTimestamp(timestamp);
        moviesRealm.setKey(DEFAULT_KEY);
        return moviesRealm;
    }

    public Movies map(MoviesRealm moviesRealm) {
        final Movies movies = new Movies();
        for (MovieRealm movieRealm : moviesRealm.getMovies()) {
            final Movie map = map(movieRealm);
            movies.add(map);
        }
        return movies;
    }
}
