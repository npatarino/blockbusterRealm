package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm;

import io.realm.RealmObject;

public class MovieRealm extends RealmObject {

    public String id;
    public String title;
    public String titleLong;
    public String coverSmallUrl;
    public String coverMediumUrl;
    public String coverLargeUrl;

}
