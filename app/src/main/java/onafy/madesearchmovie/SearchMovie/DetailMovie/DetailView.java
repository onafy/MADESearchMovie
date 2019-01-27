package onafy.madesearchmovie.SearchMovie.DetailMovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.SearchMovie.Model.Movie;
import onafy.madesearchmovie.SearchMovie.Model.MovieDetail;

public interface DetailView {
    void requestDetail(StringRequest data);
    void getDetailList(List<MovieDetail> list);

}
