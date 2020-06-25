package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.MainActivity;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually inflates a layout from an XML file and returns it in a ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //from the current context, use the item_movie.xml file to "inflate" a new view
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        // viewholder is holding a view that can display a movie (currently has no data)
        return new ViewHolder(movieView);
    }

    // Populates the data for a specific movie into an item view through the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //gets data from a certain position from the model
        Movie movie = movies.get(position);
        //binds that retrieved data to a viewholder so that the movie can be displayed in our RecyclerView
        holder.bind(movie);
    }

    // Returns the number of items in the list (so in the RecyclerView)
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // ViewHolder class holds all the views needed to display a movie
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

            // make each movie description clickable
            itemView.setOnClickListener(this);
        }

        // binds the information of a specific movie to a viewholder so it can be displayed
        public void bind(Movie movie) {
            // set title and overview text for movie
            tvOverview.setText(movie.getOverview());
            tvTitle.setText(movie.getTitle());

            //logic to determine whether phone is portrait (poster) or landscape (backdrop)
            String imageURL;
            int ph;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackdropPath();
                ph = R.drawable.flicks_backdrop_placeholder;
            } else {
                imageURL = movie.getPosterPath();
                ph = R.drawable.flicks_movie_placeholder;
            }

            // set poster/backdrop image for the movie
            Glide.with(context).load(imageURL).placeholder(ph).transform(new RoundedCornersTransformation(25, 0)).into(ivPoster);
        }

        // when clicked, a viewholder will show a new activity
        @Override
        public void onClick(View view) {
            //get position of current movie/viewholder
            int position = getAdapterPosition();

            //make sure position is valid
            if (position != RecyclerView.NO_POSITION) {

                Intent i = new Intent(context, MovieDetailsActivity.class);
                Movie movie = movies.get(position);
                //pass the current movie to the next activity
                String KEY = Movie.class.getSimpleName();
                i.putExtra("KEY", KEY);
                i.putExtra(KEY, Parcels.wrap(movie)); //need to serialize because it's an class we wrote

                //use the intent to show the new activity starting from the desired context
                context.startActivity(i);
            }
        }
    }
}
