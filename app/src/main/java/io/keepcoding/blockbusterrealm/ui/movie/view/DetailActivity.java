package io.keepcoding.blockbusterrealm.ui.movie.view;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import io.keepcoding.blockbusterrealm.R;
import io.keepcoding.blockbusterrealm.data.datasources.cache.CacheDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.cache.disk.RealmDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.NetDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.NetFakeDataSource;
import io.keepcoding.blockbusterrealm.data.repositories.DataMoviesRepository;
import io.keepcoding.blockbusterrealm.domain.business.Movie;
import io.keepcoding.blockbusterrealm.domain.business.provider.MoviesCachePolicy;
import io.keepcoding.blockbusterrealm.domain.business.provider.TimeProvider;
import io.keepcoding.blockbusterrealm.domain.usecases.GetMovie;
import io.keepcoding.blockbusterrealm.domain.usecases.ListMovies;
import io.keepcoding.blockbusterrealm.ui.movie.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements DetailView {

    public static final String MOVIE_ID = "movie_id";
    private DetailPresenter presenter;
    private View emptyCaseView;
    private ContentLoadingProgressBar progressBar;
    private Toolbar toolbar;
    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieDescription;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieImage = findViewById(R.id.iv_movie_photo);
        movieTitle = findViewById(R.id.tvTitle);
        movieDescription = findViewById(R.id.tv_movie_description);

        progressBar = findViewById(R.id.progress_bar);
        emptyCaseView = findViewById(R.id.tv_empty_case);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        final GetMovie getMovieUseCase = createGetMovieUseCase();
        presenter = new DetailPresenter(this, getMovieUseCase);

        presenter.load();
    }

    @Override public void renderMovie(final Movie movie) {
        toolbar.setTitle(movie.getTitle());
        Picasso.with(movieImage.getContext()).load(movie.getCoverLargeUrl()).into(movieImage);
        movieTitle.setText(movie.getTitleLong());
        movieDescription.setText(movie.getDescription());
    }

    @Override public void showError() {
        Toast.makeText(this, "Ops! Try it again later", Toast.LENGTH_SHORT).show();
    }

    @Override public void showEmptyCase() {
        emptyCaseView.setVisibility(View.VISIBLE);
    }

    @Override public void hideEmptyCase() {
        emptyCaseView.setVisibility(View.GONE);
    }

    @Override public void hideProgressBar() {
        progressBar.hide();
    }

    @Override public void showProgressBar() {
        progressBar.show();
    }


    private GetMovie createGetMovieUseCase() {
        TimeProvider timeProvider = new TimeProvider() {
            @Override public Long getCurrentTimeInMillis() {
                return System.currentTimeMillis();
            }
        };

        final CacheDataSource cacheDataSource = new RealmDataSource(getApplicationContext(), new MoviesCachePolicy(timeProvider), timeProvider);
        final NetDataSource netDataSource = new NetFakeDataSource(getApplicationContext());
        //final NetDataSource netDataSource = new RetrofitNetDataSource();

        return new GetMovie(getMovieId(), new DataMoviesRepository(netDataSource, cacheDataSource));
    }

    private String getMovieId() {
        return getIntent().getExtras().getString(MOVIE_ID);
    }
}
