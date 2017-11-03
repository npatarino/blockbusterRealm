package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm;

import io.realm.RealmList;

public class MoviesRealm {

    private Long timestamp;
    private RealmList<MovieRealm> movies;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    public RealmList<MovieRealm> getMovies() {
        return movies;
    }

    public void setMovies(final RealmList<MovieRealm> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }
}
