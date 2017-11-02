package io.keepcoding.blockbusterrealm.data.datasources.cache.disk;

import android.content.Context;
import io.keepcoding.blockbusterrealm.data.datasources.cache.CacheDataSource;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDataSource implements CacheDataSource {

    public RealmDataSource(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1).name("blockbuster.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    @Override public Long setCache(final Movies movies) {
        final Realm realm = Realm.getDefaultInstance();
        realm.close();
        return null;
    }

    @Override public Movies getChachedMovies() throws InvalidCacheException {
        return null;
    }

    @Override public Movie getChachedMovie(final String id) throws InvalidCacheException, NotFoundException {
        return null;
    }
}
