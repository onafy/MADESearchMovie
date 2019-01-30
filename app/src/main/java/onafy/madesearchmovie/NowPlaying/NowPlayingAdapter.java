package onafy.madesearchmovie.NowPlaying;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.Util.EndPoint;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovieNP;


    public ArrayList<Movie> getListMovieNP() {
        return listMovieNP;
    }

    public void setListMovie(ArrayList<Movie> listMovieNP) {
        this.listMovieNP = listMovieNP;
    }

    NowPlayingAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new NowPlayingViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingViewHolder holder, int position) {
        holder.tv_movieName.setText(getListMovieNP().get(position).getMovieTitle());
        holder.tv_movieOverview.setText(getListMovieNP().get(position).getMovieOverview());
        String imgPoster = EndPoint.IMG_PATH + getListMovieNP().get(position).getMoviePoster();
        Glide.with(context).load(imgPoster).into(holder.img_moviePoster);

    }

    @Override
    public int getItemCount() {
        return getListMovieNP().size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder {
       @BindView(R.id.tv_movie_name)
            TextView tv_movieName;
        @BindView(R.id.tv_movie_overview)
                TextView tv_movieOverview;
        @BindView(R.id.img_movie_poster)
                ImageView img_moviePoster;

        NowPlayingViewHolder(@NonNull View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);
        }
    }
}
