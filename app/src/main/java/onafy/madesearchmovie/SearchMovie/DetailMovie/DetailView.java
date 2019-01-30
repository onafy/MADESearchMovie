package onafy.madesearchmovie.SearchMovie.DetailMovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.Model.Genre;
import onafy.madesearchmovie.Model.MovieDetail;

public interface DetailView {
    void requestDetail(StringRequest data);

    void getDetailList(List<MovieDetail> list);

    void getGenre(List<Genre> genres);

}
