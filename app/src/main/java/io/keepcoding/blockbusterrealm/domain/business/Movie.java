package io.keepcoding.blockbusterrealm.domain.business;

public final class Movie {

    private final String id;
    private final String title;
    private final String titleLong;
    private final String coverSmallUrl;
    private final String coverMediumUrl;
    private final String coverLargeUrl;
    private final String description;

    public Movie(final String id, final String title, final String titleLong, final String coverSmallUrl, final String coverMediumUrl,
                 final String coverLargeUrl, final String description) {
        this.id = id;
        this.title = title;
        this.titleLong = titleLong;
        this.coverSmallUrl = coverSmallUrl;
        this.coverMediumUrl = coverMediumUrl;
        this.coverLargeUrl = coverLargeUrl;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    public String getCoverMediumUrl() {
        return coverMediumUrl;
    }

    public String getCoverLargeUrl() {
        return coverLargeUrl;
    }

    @Override public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final Movie movie = (Movie) o;

        return id.equals(movie.id);
    }

    @Override public int hashCode() {
        return id.hashCode();
    }

    public String getDescription() {
        return description;
    }
}
