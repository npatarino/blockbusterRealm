package io.keepcoding.blockbusterrealm.data.datasources.net.services;

import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieResponse;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movies.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesService {

    @GET("list_movies.jsonp?minimum_rating=8&limit=50") Call<MoviesResponse> listMovies();

    @GET("movie_details.jsonp") Call<MovieResponse> movie(String movie_id);
}
