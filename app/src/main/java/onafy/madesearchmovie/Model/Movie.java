package onafy.madesearchmovie.Model;

import org.json.JSONObject;

public class Movie {

    public String id;
    private String movieTitle;
    private String movieOverview;
    private String moviePoster;

    public  Movie(JSONObject object){
        try {
            String movieId = object.getString("id");
            String movieTitle = object.getString("title");
            String movieOverview = object.getString("overview");
            String  moviePoster =  object.getString("poster_path");
            this.id = movieId;
            this.movieTitle = movieTitle;
            this.movieOverview = movieOverview;
            this.moviePoster = moviePoster;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMoviePoster() {
        return moviePoster;
    }


}
