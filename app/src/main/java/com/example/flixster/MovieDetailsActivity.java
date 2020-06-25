package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.databinding.ActivityMainBinding;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    TextView tvTitle;
    RatingBar rbVoteAverage;
    TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // unwrapping the movie that was passed through an intent while opening this activity
        String KEY = getIntent().getStringExtra("KEY");
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(KEY));
        // confirm movie has unwrapped
        Log.d("MovieDetailsActivity", String.format("Movie title: %s", movie.getTitle()));

        // set title and overview text
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        // VoteAverage is [0, 10] so divide by 2 to get num stars for rating
        binding.rbVoteAverage.setRating((float) (movie.getVoteAverage() / 2.0));

    }
}