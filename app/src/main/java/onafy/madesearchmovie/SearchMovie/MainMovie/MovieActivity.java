package onafy.madesearchmovie.SearchMovie.MainMovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import onafy.madesearchmovie.NowPlaying.NowPlayingActivity;
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.SearchMovie.DetailMovie.DetailActivity;
import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.UpcomingActivity;
import onafy.madesearchmovie.Util.ItemClickSupport;

public class MovieActivity extends AppCompatActivity implements MovieView{
    RecyclerView recyclerView;
    ArrayList<Movie> movieItems;
    ListMovieAdapter adapter;
    EditText etTitle;
    Button btnSearch;
    String title;
    MoviePresenter presenter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        setupVar();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListMovieAdapter(this);
        adapter.setListMovie(movieItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            title = "a";
        }

        presenter = new MoviePresenter(this);
        presenter.getMovieItems(title);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    title = "a";
                }
                presenter.getMovieItems(title);
            }
        });


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent in = new Intent(getApplicationContext(), DetailActivity.class);
                in.putExtra("movieId", movieItems.get(position).getId());
                startActivity(in);;
            }
        });
    }


    public void setupVar(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        etTitle = (EditText) findViewById(R.id.et_title);
        btnSearch = (Button) findViewById(R.id.btn_searchTitle);
        movieItems = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void requestMovie(StringRequest data) {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(data);
    }

    @Override
    public void getMovieList(List<Movie> list) {
        movieItems.clear();
        movieItems.addAll(list);
        Log.d("movieItemsActivity", movieItems.toString());
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.now_playing:
                Intent i = new Intent(this, NowPlayingActivity.class);
                startActivity(i);
                return true;
            case R.id.upcoming:
                Intent k = new Intent(this,UpcomingActivity.class);
                startActivity(k);
                return true;
            default:
                return true;
        }
    }


}
