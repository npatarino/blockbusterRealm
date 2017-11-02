package io.keepcoding.blockbusterrealm.data.datasources.net;

import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NetworkException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;

public interface NetDataSource {

    Movies getAll() throws NetworkException, NotFoundException;

    Movie getById(String id) throws NotFoundException, NetworkException;
}
