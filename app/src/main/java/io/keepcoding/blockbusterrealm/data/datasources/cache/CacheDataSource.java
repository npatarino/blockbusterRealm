package io.keepcoding.blockbusterrealm.data.datasources.cache;

import android.annotation.SuppressLint;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import io.keepcoding.blockbusterrealm.domain.business.provider.MoviesCachePolicy;
import io.keepcoding.blockbusterrealm.domain.business.provider.TimeProvider;
import java.util.Iterator;

public class CacheDataSource {

    private final TimeProvider timeProvider;
    private final MoviesCachePolicy moviesCachePolicy;
    private Movies cachedMovies;
    private Long cachedMoviesTime;

    @SuppressLint("UseSparseArrays") public CacheDataSource(final TimeProvider timeProvider, final MoviesCachePolicy moviesCachePolicy) {
        this.timeProvider = timeProvider;
        this.moviesCachePolicy = moviesCachePolicy;
        cachedMovies = new Movies();
        cachedMoviesTime = 0L;
    }

    public Long setCache(Movies movies) {
        final Long currentTimeInMillis = timeProvider.getCurrentTimeInMillis();
        cachedMovies = movies;
        cachedMoviesTime = currentTimeInMillis;
        return currentTimeInMillis;
    }

    public Movies getChachedMovies() throws InvalidCacheException {
        if (moviesCachePolicy.areMoviesValid(cachedMoviesTime)) {
            return cachedMovies;
        } else {
            throw new InvalidCacheException("");
        }
    }

    public Movie getChachedMovie(String id) throws InvalidCacheException, NotFoundException {
        if (moviesCachePolicy.areMoviesValid(cachedMoviesTime)) {
            Iterator<Movie> iterator = cachedMovies.iterator();
            while (iterator.hasNext()) {
                final Movie movie = iterator.next();
                if (movie.getId().equalsIgnoreCase(id)) {
                    return movie;
                }
            }
            throw new NotFoundException("");
        } else {
            throw new InvalidCacheException("");
        }
    }
}
