package onafy.madesearchmovie.searchmovie.detailmovie;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.model.Genre;
import onafy.madesearchmovie.model.MovieDetail;

public interface DetailView {
    void requestDetail(StringRequest data);

    void getDetailList(List<MovieDetail> list);

    void getGenre(List<Genre> genres);

}
