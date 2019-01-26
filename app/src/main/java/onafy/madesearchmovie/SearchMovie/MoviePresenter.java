package onafy.madesearchmovie.SearchMovie;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class MoviePresenter {
    private final MovieView view;

    private static final String API_KEY = "a6c6f006aae23d630f6c5bd091b1cea2";

    public MoviePresenter(MovieView view) {
        this.view = view;
    }


    public MovieView getView() {
        return view;
    }


    public void getMovieItems(String title){
        view.showLoading();
        final ArrayList<Movie> movieItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +
                API_KEY + "&language=en-US&query=" + title;
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String responses = response.toString();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(responses);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject movies = jsonArray.getJSONObject(i);
                                Movie movieModel = new Movie(movies);
                                movieItems.add(movieModel);
                                view.getMovieList(movieItems);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        );
        view.hideLoading();
        view.requestMovie(getRequest);
    }

}