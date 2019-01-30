package onafy.madesearchmovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.Model.Movie;

public interface UpcomingView {
    void requestMovieUpcoming(StringRequest data);

    void getMovieUpcomingList(List<Movie> list);
}
