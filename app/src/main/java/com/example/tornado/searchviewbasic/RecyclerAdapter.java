package com.example.tornado.searchviewbasic;

/**
 * Created by Tornado on 7/19/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tornado on 6/29/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    List<MoviesModel> movieList;
    Context context;


    public RecyclerAdapter(List<MoviesModel> movielist, Context context) {
        this.movieList = movielist;
        this.context = context;
    }



    public void setMovieList(List<MoviesModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }




    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {

        MoviesModel moviesModel=movieList .get(position);
        holder.textView.setText(moviesModel.getTitle().toString());

//        holder.textView.setText(movieList.get(position).getTitle().toString());

        Picasso.with(context)
                .load(moviesModel.getImageUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;
    }


    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyviewHolder(View itemView) {
            super(itemView);

            textView=(TextView)itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }




    //for search
//    public void updateList(List<MoviesModel> newList){
//
//        movieList=new ArrayList<>();
//        movieList.addAll(newList);
//        notifyDataSetChanged();
//    }

       public void setFilter(List<MoviesModel> newList){

        movieList=new ArrayList<>();
        movieList.addAll(newList);
        notifyDataSetChanged();

        }

    }
