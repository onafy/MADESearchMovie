package onafy.madesearchmovie.nowplaying;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.model.Movie;
import onafy.madesearchmovie.searchmovie.detailmovie.DetailActivity;
import onafy.madesearchmovie.util.ItemClickSupport;

public class NowPlayingFragment extends Fragment implements NowPlayingView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ArrayList<Movie> movieNPItems;
    NowPlayingAdapter adapter;
    NowPlayingPresenter presenter;



    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this, view);

        setup();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent in = new Intent(getActivity(), DetailActivity.class);
                in.putExtra("movieId", movieNPItems.get(position).getId());
                startActivity(in);
            }
        });

        return view;
    }

    private void setup(){
        movieNPItems = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NowPlayingAdapter(getActivity());
        adapter.setListMovie(movieNPItems);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        presenter = new NowPlayingPresenter(this);
        presenter.getMovieNPItems();
    }


    @Override
    public void requestMovieNP(StringRequest data) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
