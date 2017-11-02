package io.keepcoding.blockbusterrealm.data.datasources.cache;

import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;

public interface CacheDataSource {

    Long setCache(Movies movies);

    Movies getChachedMovies() throws InvalidCacheException;

    Movie getChachedMovie(String id) throws InvalidCacheException, NotFoundException;
}
