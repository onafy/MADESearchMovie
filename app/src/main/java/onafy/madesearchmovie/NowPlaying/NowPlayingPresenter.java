package onafy.madesearchmovie.NowPlaying;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.Util.EndPoint;

public class NowPlayingPresenter {
    private final NowPlayingView view;

    public NowPlayingPresenter(NowPlayingView view) {
        this.view = view;
    }

    public NowPlayingView getView() {
        return view;
    }

    public void getMovieNPItems() {
        final ArrayList<Movie> movieNPItems = new ArrayList<>();
        StringRequest getRequest = new StringRequest(Request.Method.GET, EndPoint.NOW_PLAYING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String responses = response.toString();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(responses);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject movies = jsonArray.getJSONObject(i);
                                Movie movieNPModel = new Movie(movies);
                                movieNPItems.add(movieNPModel);
                                view.getMovieNPList(movieNPItems);

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
        view.requestMovieNP(getRequest);
    }

}
