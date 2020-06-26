package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.databinding.ActivityMainBinding;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // unwrapping the movie that was passed through an intent while opening this activity
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        // confirm movie has unwrapped
        Log.d("MovieDetailsActivity", String.format("Movie title: %s", movie.getTitle()));

        // set title and overview text
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        // VoteAverage is [0, 10] so divide by 2 to get num stars for rating
        binding.rbVoteAverage.setRating((float) (movie.getVoteAverage() / 2.0));
        Glide.with(this).load(movie.getBackdropPath()).placeholder(R.drawable.flicks_backdrop_placeholder).into(binding.ivBackdrop);

        binding.ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackdropClick(view);
            }
        });



    }

    private void onBackdropClick(View view) {
        if (movie.getVideoID() != "") {

            //if the movie has a videoID, use an intent to open a MovieTrailerActivity and pass that videoID to the new activity
            Intent i = new Intent(this, MovieTrailerActivity.class);
            i.putExtra("videoID", movie.getVideoID());
            this.startActivity(i);
        }

    }
}