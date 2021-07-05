package com.route.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.route.newsapp.model.NewsResponse.ArticlesItem;

import java.util.List;

/**
 * Created by Mohamed Nabil Mohamed on 10/17/2019.
 * m.nabil.fci2015@gmail.com
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<ArticlesItem> articles;

    public NewsAdapter(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesItem item = articles.get(position);
        holder.title.setText(item.title);
        holder.content.setText(item.description);
        holder.date.setText(item.publishedAt);//Todo:search how to format time
        holder.sourceName.setText(item.source.getName());

        Glide.with(holder.itemView)
                .load(item.urlToImage)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articles==null?0:articles.size();
    }
    public void changeData(List<ArticlesItem> items){
        this.articles=items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView sourceName;
         ImageView image;
         TextView content;
        TextView  title, date;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);
            sourceName =  rootView.findViewById(R.id.source_name);
            title =  rootView.findViewById(R.id.title);
            date =  rootView.findViewById(R.id.date);
            image =  rootView.findViewById(R.id.image);
            content =  rootView.findViewById(R.id.content);
        }
    }
}
