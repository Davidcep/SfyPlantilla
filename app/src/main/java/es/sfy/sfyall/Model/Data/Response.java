package es.sfy.sfyall.Model.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("results") List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
