package io.keepcoding.blockbusterrealm.ui.movies.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.keepcoding.blockbusterrealm.R;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.ui.movies.presenter.MainPresenter;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final MainPresenter presenter;
    private Movies movies;

    public MoviesAdapter(final MainPresenter presenter) {
        this.presenter = presenter;
        this.movies = new Movies();
    }

    public void add(Movies collection) {
        movies = collection;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieViewHolder(view, presenter);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;
        Movie movie = movies.get(position);
        if (movie != null) {
            viewHolder.render(movie);
        } else {
            Log.e("MoviesAdapter", "Error rendering a movie (" + position + ")");
        }
    }

    @Override public int getItemCount() {
        return movies.size();
    }
}
