package io.keepcoding.blockbusterrealm.data.datasources.net.entities.movies;

import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieEntity;

public class MoviesContentResponse {

    public String limit;
    public MovieEntity[] movies;
    public String page_number;
    public String movie_count;
}