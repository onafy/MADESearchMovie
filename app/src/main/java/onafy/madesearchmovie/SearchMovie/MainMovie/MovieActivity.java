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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import onafy.madesearchmovie.NowPlaying.NowPlayingActivity;
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.SearchMovie.DetailMovie.DetailActivity;
import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.UpcomingActivity;
import onafy.madesearchmovie.Util.ItemClickSupport;

public class MovieActivity extends AppCompatActivity implements MovieView{
    @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
    @BindView(R.id.et_title)
        EditText etTitle;
    @BindView(R.id.btn_searchTitle)
        Button btnSearch;
    @BindView(R.id.progressBar)
        ProgressBar progressBar;

    ArrayList<Movie> movieItems;
    ListMovieAdapter adapter;
    String title;
    MoviePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        movieItems = new ArrayList<>();

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



        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent in = new Intent(getApplicationContext(), DetailActivity.class);
                in.putExtra("movieId", movieItems.get(position).getId());
                startActivity(in);;
            }
        });
    }


    @OnClick(R.id.btn_searchTitle)
    public void onButtonClick(View view) {
        String title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            title = "a";
        }
        presenter.getMovieItems(title);
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