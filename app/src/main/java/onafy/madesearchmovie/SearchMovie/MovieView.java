package onafy.madesearchmovie.SearchMovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

public interface MovieView {
    void showLoading();
    void hideLoading();
    void requestMovie(StringRequest data);
    void getMovieList(List<Movie> list);

}
