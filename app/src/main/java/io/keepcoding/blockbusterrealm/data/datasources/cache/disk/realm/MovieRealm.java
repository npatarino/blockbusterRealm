package io.keepcoding.blockbusterrealm.data.datasources.cache.disk.realm;

import io.realm.RealmObject;

public class MovieRealm extends RealmObject {

    private String id;
    private String title;
    private String titleLong;
    private String coverSmallUrl;
    private String coverMediumUrl;
    private String coverLargeUrl;
    private String description;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public void setTitleLong(final String titleLong) {
        this.titleLong = titleLong;
    }

    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    public void setCoverSmallUrl(final String coverSmallUrl) {
        this.coverSmallUrl = coverSmallUrl;
    }

    public String getCoverMediumUrl() {
        return coverMediumUrl;
    }

    public void setCoverMediumUrl(final String coverMediumUrl) {
        this.coverMediumUrl = coverMediumUrl;
    }

    public String getCoverLargeUrl() {
        return coverLargeUrl;
    }

    public void setCoverLargeUrl(final String coverLargeUrl) {
        this.coverLargeUrl = coverLargeUrl;
    }
}
