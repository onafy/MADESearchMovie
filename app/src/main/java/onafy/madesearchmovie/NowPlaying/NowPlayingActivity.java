package onafy.madesearchmovie.NowPlaying;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.R;

public class NowPlayingActivity extends AppCompatActivity implements NowPlayingView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ArrayList<Movie> movieNPItems;
    NowPlayingAdapter adapter;
    String title;
    NowPlayingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ButterKnife.bind(this);

        movieNPItems = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NowPlayingAdapter(this);
        adapter.setListMovie(movieNPItems);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        presenter = new NowPlayingPresenter(this);
        presenter.getMovieNPItems();

        showActionBar();


    }

    private void showActionBar() {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(R.string.now_playing);
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void requestMovieNP(StringRequest data) {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(data);
    }

    @Override
    public void getMovieNPList(List<Movie> list) {
        movieNPItems.clear();
        movieNPItems.addAll(list);
        Log.d("movieItemsActivity", movieNPItems.toString());
        adapter.notifyDataSetChanged();
    }
}
