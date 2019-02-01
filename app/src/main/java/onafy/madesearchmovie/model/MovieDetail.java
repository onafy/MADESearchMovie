package onafy.madesearchmovie.model;

import org.json.JSONObject;

public class MovieDetail {


    private String moviePoster;
    private String movieTitle;
    private String movieTagline;
    private String movieRating;
    private String movieDuration;
    private String movieLanguage;
    private String movieRelease;
    private String movieOverview;

    public MovieDetail(JSONObject object) {
        try {
            String moviePoster = object.getString("poster_path");
            String movieTitle = object.getString("original_title");
            String movieTagline = object.getString("tagline");
            String movieRating = object.getString("vote_average");
            String movieDuration = object.getString("runtime");
            String movieLanguage = object.getString("original_language");
            String movieRelease = object.getString("release_date");
            String movieOverview = object.getString("overview");

            this.moviePoster = moviePoster;
            this.movieTitle = movieTitle;
            this.movieTagline = movieTagline;
            this.movieRating = movieRating;
            this.movieDuration = movieDuration;
            this.movieLanguage = movieLanguage;
            this.movieRelease = movieRelease;
            this.movieOverview = movieOverview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieTagline() {
        return movieTagline;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

}
