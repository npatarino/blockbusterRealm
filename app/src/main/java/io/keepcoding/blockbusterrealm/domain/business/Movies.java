package io.keepcoding.blockbusterrealm.domain.business;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class Movies {

    private final List<Movie> list;

    public Movies() {
        list = new ArrayList<>();
    }

    @Nullable public Movie get(int position) {
        if (position > list.size()) {return null;}

        return list.get(position);
    }

    public int size() {
        return list.size();
    }

    public Movie add(final Movie item) {
        list.add(item);
        return item;
    }

    public Iterator<Movie> iterator() {
        return list.iterator();
    }

    private void addAll(final Collection<Movies> collection) {

    }

    public boolean isEmpty() {
        return size() <= 0;
    }
}
