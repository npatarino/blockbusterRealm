package io.keepcoding.blockbusterrealm.data.datasources.cache.disk;

import android.content.Context;
import io.keepcoding.blockbusterrealm.data.datasources.cache.CacheDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MoviesRealm;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.mappers.MoviesRealmMapper;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import io.keepcoding.blockbusterrealm.domain.business.provider.MoviesCachePolicy;
import io.keepcoding.blockbusterrealm.domain.business.provider.TimeProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDataSource implements CacheDataSource {

    private final MoviesCachePolicy policy;
    private final TimeProvider timeProvider;

    public RealmDataSource(Context context, MoviesCachePolicy policy, TimeProvider timeProvider) {
        this.policy = policy;
        this.timeProvider = timeProvider;
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1).name("blockbuster.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    @Override public Long setCache(final Movies movies) {
        final Realm realm = Realm.getDefaultInstance();
        final MoviesRealmMapper moviesRealmMapper = new MoviesRealmMapper();
        final Long currentTimeInMillis = timeProvider.getCurrentTimeInMillis();
        final MoviesRealm moviesRealm = moviesRealmMapper.map(movies, currentTimeInMillis);
        realm.executeTransaction(new Realm.Transaction() {
            @Override public void execute(final Realm realm) {
                realm.copyToRealmOrUpdate(moviesRealm);
            }
        });
        realm.close();
        return currentTimeInMillis;
    }

    @Override public Movies getChachedMovies() throws InvalidCacheException {
        return null;
    }

    @Override public Movie getChachedMovie(final String id) throws InvalidCacheException, NotFoundException {
        return null;
    }
}
