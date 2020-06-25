package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

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
        setContentView(R.layout.activity_movie_details);

        // unwrapping the movie that was passed through an intent while opening this activity
        String KEY = getIntent().getStringExtra("KEY");
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(KEY));
        // confirm movie has unwrapped
        Log.d("MovieDetailsActivity", String.format("Movie title: %s", movie.getTitle()));

        tvTitle = findViewById(R.id.tvTitle);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        tvOverview = findViewById(R.id.tvOverview);

        // set title and overview text
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        // VoteAverage is [0, 10] so divide by 2 to get num stars for rating
        rbVoteAverage.setRating((float) (movie.getVoteAverage() / 2.0));

    }
}