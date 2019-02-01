package onafy.madesearchmovie.upcoming;

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

public class UpcomingPresenter {
    private final UpcomingView view;

    public UpcomingPresenter(UpcomingView view) {
        this.view = view;
    }

    public UpcomingView getView() {
        return view;
    }

    public void getMovieUpcomingItems() {
        final ArrayList<Movie> movieUpcomingItems = new ArrayList<>();
        StringRequest getRequest = new StringRequest(Request.Method.GET, EndPoint.UPCOMING,
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
                                Movie movieUpcomingModel = new Movie(movies);
                                movieUpcomingItems.add(movieUpcomingModel);
                                view.getMovieUpcomingList(movieUpcomingItems);

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
        view.requestMovieUpcoming(getRequest);
    }

}

