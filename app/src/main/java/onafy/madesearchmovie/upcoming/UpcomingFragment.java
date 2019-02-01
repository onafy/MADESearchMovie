package onafy.madesearchmovie.upcoming;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements UpcomingView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ArrayList<Movie> movieUpcomingItems;
    UpcomingAdapter adapter;
    UpcomingPresenter presenter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);

        setup();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent in = new Intent(getActivity(), DetailActivity.class);
                in.putExtra("movieId", movieUpcomingItems.get(position).getId());
                startActivity(in);
            }
        });

        return view;
    }

    private void setup(){
        movieUpcomingItems = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new UpcomingAdapter(getActivity());
        adapter.setListMovieUpcoming(movieUpcomingItems);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        presenter = new UpcomingPresenter(this);
        presenter.getMovieUpcomingItems();
    }


    @Override
    public void requestMovieUpcoming(StringRequest data) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(data);
    }

    @Override
    public void getMovieUpcomingList(List<Movie> list) {
        movieUpcomingItems.clear();
        movieUpcomingItems.addAll(list);
        Log.d("movieItemsActivity", movieUpcomingItems.toString());
        adapter.notifyDataSetChanged();
    }

}
