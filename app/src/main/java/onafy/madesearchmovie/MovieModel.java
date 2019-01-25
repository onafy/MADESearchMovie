package onafy.madesearchmovie;

import org.json.JSONObject;

public class MovieModel {
    private String movieTitle;
    private String movieOverview;
    private String moviePoster;
    private String movieVote;
    private String movieLanguage;
    private String[] movieGenre;
    private String movieRelease;
    public  MovieModel(JSONObject object){
        try {
            String movieTitle = object.getString("title");
            String movieOverview = object.getString("overview");
            String  moviePoster =  object.getString("poster_path");
            this.movieTitle = movieTitle;
            this.movieOverview = movieOverview;
            this.moviePoster = moviePoster;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieVote() {
        return movieVote;
    }

    public void setMovieVote(String movieVote) {
        this.movieVote = movieVote;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String[] getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String[] movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

}
