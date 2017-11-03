package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MoviesRealm extends RealmObject {

    @PrimaryKey private String key;
    private Long timestamp;
    private RealmList<MovieRealm> movies = new RealmList<>();

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

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }
}
