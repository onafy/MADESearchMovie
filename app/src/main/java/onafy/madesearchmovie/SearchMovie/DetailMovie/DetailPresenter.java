package onafy.madesearchmovie.SearchMovie.DetailMovie;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import onafy.madesearchmovie.SearchMovie.Model.Movie;
import onafy.madesearchmovie.SearchMovie.Model.MovieDetail;
import onafy.madesearchmovie.SearchMovie.Util.EndPoint;

public class DetailPresenter {
    private final DetailView view;



    public DetailPresenter(DetailView view) {
        this.view = view;
    }


    public DetailView getView() {
        return view;
    }


    public void getDetail(String idMovie){
        final ArrayList<MovieDetail> movieDetails = new ArrayList<>();
        StringRequest getRequest = new StringRequest(Request.Method.GET, EndPoint.DETAIL_MOVIE_1+ idMovie + EndPoint.DETAIL_MOVIE_2,
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
                             MovieDetail movieDetail = new MovieDetail(jsonObject);
                             movieDetails.add(movieDetail);
                             Log.d("List MovieDetails", String.valueOf(movieDetails));
                              view.getDetailList(movieDetails);

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
        view.requestDetail(getRequest);
    }

}
