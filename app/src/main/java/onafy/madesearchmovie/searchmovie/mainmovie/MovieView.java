package onafy.madesearchmovie.searchmovie.mainmovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.model.Movie;

public interface MovieView {
    void showLoading();

    void hideLoading();

    void requestMovie(StringRequest data);

    void getMovieList(List<Movie> list);

}
