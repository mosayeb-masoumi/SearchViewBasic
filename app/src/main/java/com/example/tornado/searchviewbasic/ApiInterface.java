package com.example.tornado.searchviewbasic;

/**
 * Created by Tornado on 7/19/2018.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("volley_array.json")
    Call<List<MoviesModel>> getMovies();
}


