package io.keepcoding.blockbusterrealm.data.datasources.net;

import io.keepcoding.blockbusterrealm.data.datasources.net.entities.mappers.MoviesMapper;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieEntity;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieResponse;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movies.MoviesResponse;
import io.keepcoding.blockbusterrealm.data.datasources.net.services.MoviesService;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NetworkException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitNetDataSource implements NetDataSource {

    private final Retrofit apiClient;

    public RetrofitNetDataSource() {
        this.apiClient = new Retrofit.Builder().baseUrl("https://yts.ag/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override public Movies getAll() throws NetworkException, NotFoundException {
        final Call<MoviesResponse> listCall = apiClient.create(MoviesService.class).listMovies();

        try {
            final Response<MoviesResponse> response = listCall.execute();
            final MoviesResponse moviesResponse = response.body();
            if (moviesResponse == null) {
                throw new NotFoundException("");
            }
            return getMappedMovies(Arrays.asList(moviesResponse.data.movies));
        } catch (IOException e) {
            throw new NetworkException(e.getLocalizedMessage());
        }
    }

    @Override public Movie getById(final String id) throws NotFoundException, NetworkException {
        final Call<MovieResponse> movieCall = apiClient.create(MoviesService.class).movie(id);

        try {
            final Response<MovieResponse> response = movieCall.execute();
            final MovieResponse movieResponse = response.body();
            if (movieResponse == null) {
                throw new NotFoundException("");
            }
            return new MoviesMapper().map(movieResponse.data.movie);
        } catch (IOException e) {
            throw new NetworkException(e.getLocalizedMessage());
        }
    }

    private Movies getMappedMovies(@NotNull List<MovieEntity> movieEntities) throws NotFoundException {
        final MoviesMapper mapper = new MoviesMapper();

        Movies movies = new Movies();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(mapper.map(movieEntity));
        }
        return movies;
    }
}
