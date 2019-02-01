package onafy.madesearchmovie.nowplaying;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.model.Movie;

public interface NowPlayingView {
    void requestMovieNP(StringRequest data);

    void getMovieNPList(List<Movie> list);
}
