package onafy.madesearchmovie.SearchMovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

public class MovieActivity extends AppCompatActivity implements MovieView{
    ListView listView;
    ArrayList<Movie> movieItems;
    MovieAdapter adapter;
    EditText etTitle;
    Button btnSearch;
    String title;
    MoviePresenter presenter;
    ProgressBar progressBar;

    static final String EXTRAS_TITLE = "EXTRAS_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        listView = (ListView) findViewById(R.id.listView);
        etTitle = (EditText) findViewById(R.id.et_title);
        btnSearch = (Button) findViewById(R.id.btn_searchTitle);
        movieItems = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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
