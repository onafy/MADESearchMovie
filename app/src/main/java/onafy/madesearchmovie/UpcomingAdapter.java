package onafy.madesearchmovie;

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

import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.Util.EndPoint;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovieUpcoming;


    public ArrayList<Movie> getListMovieUpcoming() {
        return listMovieUpcoming;
    }

    public void setListMovieUpcoming(ArrayList<Movie> listMovieUpcoming) {
        this.listMovieUpcoming = listMovieUpcoming;
    }

    UpcomingAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new UpcomingViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        holder.tv_movieName.setText(getListMovieUpcoming().get(position).getMovieTitle());
        holder.tv_movieOverview.setText(getListMovieUpcoming().get(position).getMovieOverview());
        String imgPoster = EndPoint.IMG_PATH + getListMovieUpcoming().get(position).getMoviePoster();
        Glide.with(context).load(imgPoster).into(holder.img_moviePoster);

    }

    @Override
    public int getItemCount() {
        return getListMovieUpcoming().size();
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {
        TextView tv_movieName;
        TextView tv_movieOverview;
        ImageView img_moviePoster;

        UpcomingViewHolder(@NonNull View convertView) {
            super(convertView);
            tv_movieName = (TextView) convertView.findViewById(R.id.tv_movie_name);
            tv_movieOverview = (TextView) convertView.findViewById(R.id.tv_movie_overview);
            img_moviePoster = (ImageView) convertView.findViewById(R.id.img_movie_poster);
        }
    }
}
