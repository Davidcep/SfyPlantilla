package es.sfy.sfyall.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.sfy.sfyall.Model.Data.Movie;
import es.sfy.sfyall.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Movie> movies;
    private RecyclerViewClick viewClick;

    public RecyclerAdapter(List<Movie> movies, RecyclerViewClick viewClick) {
        this.movies = movies;
        this.viewClick = viewClick;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v, viewClick);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.title.setText(movies.get(position).getTitle());
        holder.date.setText(movies.get(position).getDate());
        Picasso.get().load("https://image.tmdb.org/t/p/original"+movies.get(position).getImg())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
