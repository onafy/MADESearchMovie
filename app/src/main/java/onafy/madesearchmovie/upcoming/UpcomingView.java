package onafy.madesearchmovie.upcoming;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.model.Movie;

public interface UpcomingView {
    void requestMovieUpcoming(StringRequest data);

    void getMovieUpcomingList(List<Movie> list);
}
