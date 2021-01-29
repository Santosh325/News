package com.example.news;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<Article> articles;
    private Context context;

    public NewsAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
     final Article myData = articles.get(position);
     holder.title.setText(myData.getTitle());
     holder.description.setText(myData.getAuthor());
        Picasso.get().load(myData.getUrlToImage()).into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView title;
        TextView description;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView);
            description = itemView.findViewById(R.id.textView3);
        }
    }
}
