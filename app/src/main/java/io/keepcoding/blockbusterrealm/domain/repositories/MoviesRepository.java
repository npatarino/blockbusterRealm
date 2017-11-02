package io.keepcoding.blockbusterrealm.domain.repositories;

import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NetworkException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;

public interface MoviesRepository {

    Movies getAll() throws NetworkException;

    Movie getById(String id) throws NotFoundException, NetworkException;
}
