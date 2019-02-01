package onafy.madesearchmovie.searchmovie.mainmovie;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import onafy.madesearchmovie.model.Movie;
import onafy.madesearchmovie.util.EndPoint;

public final class MoviePresenter {
    private final MovieView view;


    public MoviePresenter(MovieView view) {
        this.view = view;
    }


    public MovieView getView() {
        return view;
    }


    public void getMovieItems(String title) {
        view.showLoading();
        final ArrayList<Movie> movieItems = new ArrayList<>();
        StringRequest getRequest = new StringRequest(Request.Method.GET, EndPoint.SEARCH_MOVIE + title,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
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
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        );
        view.hideLoading();
        view.requestMovie(getRequest);
    }

}