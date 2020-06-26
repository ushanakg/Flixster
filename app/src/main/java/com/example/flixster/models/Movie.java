package com.example.flixster.models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

// Encapsulates the idea of a movie (from a JSON object)
@Parcel // makes the class parcellable
public class Movie {

    public static final String TAG = "Movie";
    public static final String MOVIE_ID_URL = "https://api.themoviedb.org/3/movie/%s/videos?api_key=bff5f9f35faf36ce85009efa1e4920d5";

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Double voteAverage;
    Integer id;
    String videoID;

    // default constructor for Parcel
    public Movie() {

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        voteAverage = jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");

        requestVideoID();

    }

    private void requestVideoID() {

        AsyncHttpClient client = new AsyncHttpClient();
        // Use the movie id to find its video id through another HTTP request
        client.get(String.format(MOVIE_ID_URL, id), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    // if it exists, get the first videoID associated with the movie
                    JSONArray results = jsonObject.getJSONArray("results");
                    if (results.length() > 0) {
                        videoID = results.getJSONObject(0).getString("key");
                    } else {
                        videoID = "";
                    }

                    Log.i(TAG, "videoID: " + videoID);

                } catch (JSONException e) {
                    Log.e(TAG, "Hit JSON exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG, "Request to get YouTube ID failed", throwable);
            }
        });
    }

    // Takes in a JSONArray and returns a list of movies
    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++) {
            movies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public String getVideoID() {
        return videoID;
    }
}
