package onafy.madesearchmovie.SearchMovie.MainMovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.Model.Movie;

public interface MovieView {
    void showLoading();

    void hideLoading();

    void requestMovie(StringRequest data);

    void getMovieList(List<Movie> list);

}
