package onafy.madesearchmovie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import onafy.madesearchmovie.Model.Movie;

public class NowPlayingActivity extends AppCompatActivity implements NowPlayingView{
    RecyclerView recyclerView;
    ArrayList<Movie> movieNPItems;
    NowPlayingAdapter adapter;
    EditText etTitle;
    Button btnSearch;
    String title;
    NowPlayingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        setupVar();
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

    private void showActionBar(){
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null) {
            actionbar.setTitle("Now Playing Movie");
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


    public void setupVar(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        etTitle = (EditText) findViewById(R.id.et_title);
        btnSearch = (Button) findViewById(R.id.btn_searchTitle);
        movieNPItems = new ArrayList<>();

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
