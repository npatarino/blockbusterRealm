package io.keepcoding.blockbusterrealm.ui.movies.view;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import io.keepcoding.blockbusterrealm.R;
import io.keepcoding.blockbusterrealm.data.datasources.cache.CacheDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.NetDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.NetFakeDataSource;
import io.keepcoding.blockbusterrealm.data.datasources.net.RetrofitNetDataSource;
import io.keepcoding.blockbusterrealm.data.repositories.DataMoviesRepository;
import io.keepcoding.blockbusterrealm.domain.business.Movies;
import io.keepcoding.blockbusterrealm.domain.business.provider.MoviesCachePolicy;
import io.keepcoding.blockbusterrealm.domain.business.provider.TimeProvider;
import io.keepcoding.blockbusterrealm.domain.usecases.ListMovies;
import io.keepcoding.blockbusterrealm.ui.movies.presenter.MainPresenter;
import io.keepcoding.blockbusterrealm.ui.movies.view.adapter.MoviesAdapter;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private MoviesAdapter adapter;
    private View emptyCaseView;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListMovies listMoviesUseCase = createListMoviesUseCase();
        presenter = new MainPresenter(this, listMoviesUseCase);
        adapter = new MoviesAdapter(presenter);

        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                presenter.reload();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        progressBar = findViewById(R.id.progress_bar);
        emptyCaseView = findViewById(R.id.tv_empty_case);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        presenter.start();
    }

    private ListMovies createListMoviesUseCase() {
        TimeProvider timeProvider = new TimeProvider() {
            @Override public Long getCurrentTimeInMillis() {
                return System.currentTimeMillis();
            }
        };

        final CacheDataSource cacheDataSource = new CacheDataSource(timeProvider, new MoviesCachePolicy(timeProvider));
        final NetDataSource netDataSource = new NetFakeDataSource(getApplicationContext());
        //final NetDataSource netDataSource = new RetrofitNetDataSource();

        return new ListMovies(new DataMoviesRepository(netDataSource, cacheDataSource));
    }

    @Override public void render(final Movies movies) {
        adapter.add(movies);
        adapter.notifyDataSetChanged();
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

    @Override public void clearMovies() {
        adapter.add(new Movies());
        adapter.notifyDataSetChanged();
    }
}
