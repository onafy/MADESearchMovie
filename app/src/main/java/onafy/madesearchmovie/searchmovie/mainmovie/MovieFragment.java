package onafy.madesearchmovie.searchmovie.mainmovie;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.model.Movie;
import onafy.madesearchmovie.searchmovie.detailmovie.DetailActivity;
import onafy.madesearchmovie.util.ItemClickSupport;


public class MovieFragment extends Fragment implements MovieView{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.btn_searchTitle)
    Button btnSearch;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    ArrayList<Movie> movieItems;
    MovieAdapter adapter;
    String title;
    MoviePresenter presenter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        setup();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent in = new Intent(getActivity(), DetailActivity.class);
                in.putExtra("movieId", movieItems.get(position).getId());
                startActivity(in);
            }
        });
        return view;
    }


    private void setup(){
        movieItems = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MovieAdapter(getActivity());
        adapter.setListMovie(movieItems);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            title = "a";
        }

        presenter = new MoviePresenter(this);
        presenter.getMovieItems(title);

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
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
