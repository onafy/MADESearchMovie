package onafy.madesearchmovie.Model;

import android.util.Log;

import org.json.JSONObject;

public class Genre {
    private String genreId;
    private String genreName;

    public Genre(JSONObject object) {
        try {
            String genreId = object.getString("id");
            String genreName = object.getString("name");
            Log.d("genreName", genreName);
            this.genreId = genreId;
            this.genreName = genreName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getGenreName() {
        return genreName;
    }


}
