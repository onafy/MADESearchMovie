package onafy.madesearchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieModel> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieModel> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieModel item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    @Override
    public MovieModel getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_row_movie, null);
            holder.tv_movieName= (TextView)convertView.findViewById(R.id.tv_movie_name);
            holder.tv_movieOverview = (TextView)convertView.findViewById(R.id.tv_movie_overview);
            holder.img_moviePoster = (ImageView)convertView.findViewById(R.id.img_movie_poster);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_movieName.setText(mData.get(position).getMovieTitle());
        holder.tv_movieOverview.setText(mData.get(position).getMovieOverview());
        String imgPoster = "https://image.tmdb.org/t/p/w342" + mData.get(position).getMoviePoster();
        Glide.with(context).load(imgPoster).into(holder.img_moviePoster);
     //   holder.img_moviePoster.setText(mData.get(position).getMo());
        return convertView;
    }
    private static class ViewHolder {
        TextView tv_movieName;
        TextView tv_movieOverview;
        ImageView img_moviePoster;
    }
}
