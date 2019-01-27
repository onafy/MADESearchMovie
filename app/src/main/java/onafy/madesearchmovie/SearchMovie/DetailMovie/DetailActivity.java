package onafy.madesearchmovie.SearchMovie.DetailMovie;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.BlurTransformation;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.SearchMovie.Model.MovieDetail;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;



public class DetailActivity extends AppCompatActivity implements DetailView {
    TextView title, tagline, rating, duration, language, release, overviewe;
    CircleImageView poster;
   ImageView blurPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();

        String id = i.getStringExtra("movieId");
        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getDetail(id);

        showActionBar();

    }


    private void showActionBar(){
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Detail Movie");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void requestDetail(StringRequest data) {
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(data);
    }


    @Override
    public void getDetailList(List<MovieDetail> lists) {
        poster = (CircleImageView) findViewById(R.id.img_detail);
        blurPoster = (ImageView) findViewById(R.id.blur);
        title = (TextView) findViewById(R.id.tv_detail_title);
        tagline = (TextView) findViewById(R.id.tv_detail_shortoverview);
        rating = (TextView) findViewById(R.id.tv_detail_rating);
        duration = (TextView) findViewById(R.id.tv_detail_duration);
        language = (TextView) findViewById(R.id.tv_detail_language);
        release = (TextView) findViewById(R.id.tv_detail_release);
        overviewe = (TextView) findViewById(R.id.tv_detail_overview);


        String path = "https://image.tmdb.org/t/p/w342" + lists.get(0).getMoviePoster();
        Glide.with(this).load(path).into(poster);
        Glide.with(this).asBitmap().load(path).apply(bitmapTransform(new BlurTransformation(6, 3))).into(blurPoster);
        title.setText(lists.get(0).getMovieTitle());
        tagline.setText(lists.get(0).getMovieTagline());
        rating.setText(lists.get(0).getMovieRating());
        duration.setText(lists.get(0).getMovieDuration());
        language.setText(lists.get(0).getMovieLanguage());
        release.setText(lists.get(0).getMovieRelease());
        overviewe.setText(lists.get(0).getMovieOverview());

    }

}
