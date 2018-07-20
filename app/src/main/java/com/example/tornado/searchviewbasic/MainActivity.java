package com.example.tornado.searchviewbasic;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<MoviesModel> movieList;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieList=new ArrayList<>();

        recyclerAdapter= new RecyclerAdapter(movieList,this);

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);


        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<List<MoviesModel>> call=apiService.getMovies();
        call.enqueue(new Callback<List<MoviesModel>>() {
            @Override
            public void onResponse(Call<List<MoviesModel>> call, Response<List<MoviesModel>> response) {

                movieList=response.body();
                recyclerAdapter.setMovieList(movieList);
                Log.d("TAG","Response = "+movieList);
            }

            @Override
            public void onFailure(Call<List<MoviesModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                Toast.makeText(MainActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);

        //these to below lines are correct
         SearchView searchView=(SearchView)menuItem.getActionView();
//       SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);


        searchView.setOnQueryTextListener(this);

        return true;
    }






//add because of impliment
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    //add because of impliment
    @Override
    public boolean onQueryTextChange(String newText) {

        newText=newText.toLowerCase();
        List<MoviesModel> newList=new ArrayList<>();

        for(MoviesModel moviesModel :movieList){

            String name =moviesModel.getTitle().toLowerCase();
            if(name.contains(newText)){

                newList.add(moviesModel);
            }
        }
         recyclerAdapter.setFilter(newList);
        return true;
    }
}
