package io.keepcoding.blockbusterrealm.domain.business.provider;

public class MoviesCachePolicy {

    private static final int MOVIES_CACHE_VALID_IN_MILLIS = 5000;
    private final TimeProvider timeProvider;

    public MoviesCachePolicy(final TimeProvider timeProvider) {this.timeProvider = timeProvider;}

    public Boolean areMoviesValid(Long originalTime) {
        return ( timeProvider.getCurrentTimeInMillis() - originalTime <= MOVIES_CACHE_VALID_IN_MILLIS );
    }
}
