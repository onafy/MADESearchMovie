package onafy.madesearchmovie.searchmovie.mainmovie;

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
import onafy.madesearchmovie.R;
import onafy.madesearchmovie.model.Movie;
import onafy.madesearchmovie.util.EndPoint;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovie;


    private ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    MovieAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MovieAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tv_movieName.setText(getListMovie().get(position).getMovieTitle());
        holder.tv_movieOverview.setText(getListMovie().get(position).getMovieOverview());
        String imgPoster = EndPoint.IMG_PATH + getListMovie().get(position).getMoviePoster();
        Glide.with(context).load(imgPoster).into(holder.img_moviePoster);

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_movie_name)
        TextView tv_movieName;
        @BindView(R.id.tv_movie_overview)
        TextView tv_movieOverview;
        @BindView(R.id.img_movie_poster)
        ImageView img_moviePoster;

        CategoryViewHolder(@NonNull View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);
        }
    }
}