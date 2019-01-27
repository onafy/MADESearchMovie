package onafy.madesearchmovie.SearchMovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import onafy.madesearchmovie.R;
import onafy.madesearchmovie.SearchMovie.DetailMovie.DetailActivity;
import onafy.madesearchmovie.SearchMovie.Model.Movie;

public class MovieActivity extends AppCompatActivity implements MovieView{
    ListView listView;
    ArrayList<Movie> movieItems;
    MovieAdapter adapter;
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

        adapter = new MovieAdapter(this, movieItems);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie) adapterView.getItemAtPosition(i);

                Intent in = new Intent(getApplicationContext(), DetailActivity.class);
                in.putExtra("movieId", movie.getId());
                startActivity(in);
            }
        });
    }


    public void setupVar(){
        listView = (ListView) findViewById(R.id.listView);
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


}
