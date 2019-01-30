package onafy.madesearchmovie.SearchMovie.MainMovie;

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

import onafy.madesearchmovie.R;
import onafy.madesearchmovie.Model.Movie;
import onafy.madesearchmovie.Util.EndPoint;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovie;


    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    ListMovieAdapter(Context context) {
        this.context = context;
    }




    @NonNull
    @Override
    public ListMovieAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        TextView tv_movieName;
        TextView tv_movieOverview;
        ImageView img_moviePoster;
        CategoryViewHolder(@NonNull View convertView) {
            super(convertView);
            tv_movieName= (TextView)convertView.findViewById(R.id.tv_movie_name);
            tv_movieOverview = (TextView)convertView.findViewById(R.id.tv_movie_overview);
            img_moviePoster = (ImageView)convertView.findViewById(R.id.img_movie_poster);
        }
    }
}