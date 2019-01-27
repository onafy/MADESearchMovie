package onafy.madesearchmovie.SearchMovie.Model;

import android.util.Log;

import org.json.JSONObject;

public class Genre {
    private String genreId;
    private String genreName;

    public  Genre(JSONObject object){
        try {
            String genreId = object.getString("id");
            String genreName = object.getString("name");
            Log.d("genreName", genreName);
            this.genreId = genreId;
            this.genreName = genreName;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }


}
