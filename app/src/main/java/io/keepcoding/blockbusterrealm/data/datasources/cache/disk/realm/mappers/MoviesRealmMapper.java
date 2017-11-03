package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.mappers;

import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MovieRealm;
import io.keepcoding.blockbusterrealm.domain.business.Movie;

public class MoviesRealmMapper {

    public Movie map(MovieRealm movieRealm) {

        return new Movie(movieRealm.getId(),
                         movieRealm.getTitle(),
                         movieRealm.getTitleLong(),
                         movieRealm.getCoverSmallUrl(),
                         movieRealm.getCoverMediumUrl(),
                         movieRealm.getCoverLargeUrl());
    }

    public MovieRealm map(Movie movie) {
        final MovieRealm movieRealm = new MovieRealm();
        movieRealm.setId(movie.getId());
        movieRealm.setTitle(movie.getTitle());
        movieRealm.setTitleLong(movie.getTitleLong());
        movieRealm.setCoverSmallUrl(movie.getCoverSmallUrl());
        movieRealm.setCoverMediumUrl(movie.getCoverMediumUrl());
        movieRealm.setCoverLargeUrl(movie.getCoverLargeUrl());
        return movieRealm;
    }
}
