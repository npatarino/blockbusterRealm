package io.keepcoding.blockbusterrealm.data.datasources.cache.disk;

import android.content.Context;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import io.keepcoding.blockbusterrealm.data.datasources.cache.CacheDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MovieRealm;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.MoviesRealm;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.mappers.MoviesRealmMapper;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.migration.MovieMigration;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.InvalidCacheException;
import io.keepcoding.blockbusterrealm.domain.business.exceptions.NotFoundException;
import io.keepcoding.blockbusterrealm.domain.business.provider.MoviesCachePolicy;
import io.keepcoding.blockbusterrealm.domain.business.provider.TimeProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDataSource implements CacheDataSource {

    public static final int SCHEMA_VERSION = 2;
    private final MoviesCachePolicy policy;
    private final TimeProvider timeProvider;

    public RealmDataSource(Context context, MoviesCachePolicy policy, TimeProvider timeProvider) {
        this.policy = policy;
        this.timeProvider = timeProvider;
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .migration(new MovieMigration())
            .name("blockbuster.realm")
            .build();
        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
            Stetho.newInitializerBuilder(context)
                  .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                  .enableWebKitInspector(RealmInspectorModulesProvider.builder(context).build())
                  .build());
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
        final Realm realm = Realm.getDefaultInstance();
        final MoviesRealm moviesRealm = realm.where(MoviesRealm.class).equalTo("key", MoviesRealmMapper.DEFAULT_KEY).findFirst();
        if (moviesRealm == null) {
            throw new InvalidCacheException("Empty cache");
        }

        if (!policy.areMoviesValid(moviesRealm.getTimestamp())) {
            realm.close();
            throw new InvalidCacheException("Invalid cache");
        }

        final MoviesRealmMapper moviesRealmMapper = new MoviesRealmMapper();
        final Movies movies = moviesRealmMapper.map(moviesRealm);
        realm.close();
        return movies;
    }

    @Override public Movie getChachedMovie(final String id) throws InvalidCacheException, NotFoundException {
        final Realm realm = Realm.getDefaultInstance();
        final MovieRealm movieRealm = realm.where(MovieRealm.class).equalTo("id", id).findFirst();
        if (movieRealm == null) {
            realm.close();
            throw new NotFoundException("");
        }
        final Movie movie = new MoviesRealmMapper().map(movieRealm);
        realm.close();
        return movie;
    }
}
