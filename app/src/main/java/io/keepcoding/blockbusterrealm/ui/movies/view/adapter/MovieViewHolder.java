package io.keepcoding.blockbusterrealm.ui.movies.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import io.keepcoding.blockbusterrealm.R;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.ui.movies.presenter.MainPresenter;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final MainPresenter presenter;
    private ImageView cover;
    private TextView title;

    public MovieViewHolder(View itemView, MainPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        cover = itemView.findViewById(R.id.ivCover);
        title = itemView.findViewById(R.id.tvTitle);
    }

    public void render(Movie movie) {
        hookListeners(movie);
        renderSuperHeroPhoto(movie.getCoverLargeUrl());
        renderSuperHeroName(movie.getTitle());
    }

    private void hookListeners(final Movie movie) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.onMovieClicked(movie);
            }
        });
    }

    private void renderSuperHeroPhoto(String photo) {
        Picasso.with(getContext()).load(photo).fit().centerCrop().error(R.drawable.on_error).into(cover);
    }

    private void renderSuperHeroName(String name) {
        title.setText(name);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
