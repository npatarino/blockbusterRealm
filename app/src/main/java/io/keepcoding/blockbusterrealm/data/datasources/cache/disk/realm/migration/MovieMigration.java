package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm.migration;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MovieMigration implements RealmMigration {

    @Override public void migrate(final DynamicRealm realm, long oldVersion, final long newVersion) {
        final RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {
            schema.create("Movie")
                 .addField("description", String.class);
            oldVersion++;
        }
    }
}
