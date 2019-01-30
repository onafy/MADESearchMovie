package onafy.madesearchmovie.Util;

import onafy.madesearchmovie.BuildConfig;

public class EndPoint {
    private static final String API_KEY = BuildConfig.API_KEY;
    public static final String SEARCH_MOVIE = BuildConfig.BASE_URL + "search/movie?api_key=" + API_KEY + "&language=en-US&query=";
    public static final String DETAIL_MOVIE_1 = BuildConfig.BASE_URL + "movie/";
    public static final String DETAIL_MOVIE_2 = "?api_key=" + API_KEY + "&language=en-US";
    public static final String NOW_PLAYING = BuildConfig.BASE_URL + "movie/now_playing?api_key=" + API_KEY + "&language=en-US";
    public static final String UPCOMING = BuildConfig.BASE_URL + "movie/upcoming?api_key=" + API_KEY + "&language=en-US";
    public static final String IMG_PATH = BuildConfig.IMG_BASE_URL + "w342/";


}
