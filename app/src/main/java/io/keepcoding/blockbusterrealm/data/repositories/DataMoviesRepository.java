package io.keepcoding.blockbusterrealm.data.repositories;

import android.util.Log;
import io.keepcoding.blockbusterrealm.data.datasources.cache.memory.CacheMemoryDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.NetDataSource;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NetworkException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import io.keepcoding.blockbusterrealm.domain.repositories.MoviesRepository;

public final class DataMoviesRepository implements MoviesRepository {

    private final NetDataSource netDataSource;
    private final CacheMemoryDataSource cacheDataSource;

    public DataMoviesRepository(final NetDataSource netDataSource, final CacheMemoryDataSource cacheDataSource) {
        this.netDataSource = netDataSource;
        this.cacheDataSource = cacheDataSource;
    }

    @Override public Movies getAll() throws NetworkException {

        try {
            final Movies chachedMovies = cacheDataSource.getChachedMovies();
            Log.d("DataMoviesRepository", "Getting movies from cache");
            return chachedMovies;
        } catch (InvalidCacheException e) {
            try {
                final Movies movies = netDataSource.getAll();
                Log.d("DataMoviesRepository", "Getting movies from net");
                cacheDataSource.setCache(movies);
                return movies;
            } catch (NotFoundException e1) {
                Log.d("DataMoviesRepository", "Error getting movies from cache and net. Return empty Movies");
                return new Movies();
            }
        }
    }

    @Override public Movie getById(final String id) throws NotFoundException, NetworkException {
        try {
            final Movie chachedMovie = cacheDataSource.getChachedMovie(id);
            Log.d("DataMoviesRepository", "Getting movies from cache");
            return chachedMovie;
        } catch (InvalidCacheException | NotFoundException e) {
            final Movie movie = netDataSource.getById(id);
            Log.d("DataMoviesRepository", "Getting movies from net");
            return movie;
        }
    }
}
