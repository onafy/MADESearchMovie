package onafy.madesearchmovie.NowPlaying;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

import onafy.madesearchmovie.Model.Movie;

public interface NowPlayingView {
    void requestMovieNP(StringRequest data);

    void getMovieNPList(List<Movie> list);
}
