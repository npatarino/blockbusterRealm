package io.keepcoding.blockbusterrealm.data.datasources.net.entities.mappers;

import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieEntity;
import io.keepcoding.blockbusterrealm.domain.business.Movie;

public class MoviesMapper {

    public Movie map(MovieEntity entity) {
        return new Movie(entity.id, entity.title, entity.title_long, entity.small_cover_image, entity.medium_cover_image, entity.large_cover_image,
                         entity.description_full);
    }
}
