package io.keepcoding.blockbusterrealm.data.datasources.net;

import android.content.Context;
import com.google.gson.Gson;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.mappers.MoviesMapper;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movie.MovieEntity;
import io.keepcoding.blockbusterrealm.data.datasources.net.entities.movies.MoviesResponse;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NetworkException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class NetFakeDataSource implements NetDataSource {

    private final Context context;

    public NetFakeDataSource(final Context context) {this.context = context;}

    @Override public Movies getAll() throws NetworkException, NotFoundException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final int identifier = context.getResources().getIdentifier("list_movies", "raw", context.getPackageName());
        InputStream inputStream = context.getResources().openRawResource(identifier);
        final String textFile = readTextFile(inputStream);
        final MoviesResponse moviesResponse = new Gson().fromJson(textFile, MoviesResponse.class);
        if (moviesResponse == null) {
            throw new NotFoundException("");
        }
        return getMappedMovies(Arrays.asList(moviesResponse.data.movies));
    }

    @Override public Movie getById(final String id) throws NotFoundException, NetworkException {
        return new Movie("", "", "", "", "", "");
    }

    private Movies getMappedMovies(@NotNull List<MovieEntity> movieEntities) throws NotFoundException {
        final MoviesMapper mapper = new MoviesMapper();

        Movies movies = new Movies();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(mapper.map(movieEntity));
        }
        return movies;
    }

    private String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while (( len = inputStream.read(buf) ) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
