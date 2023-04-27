package com.example.cooktogether.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooktogether.data.models.Newsfeed;
import com.example.cooktogether.databinding.ItemNewsfeedBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterProfile extends RecyclerView.Adapter<RecyclerViewAdapterProfile.RecyclerViewProfileItemViewHolder> {
    
    List<Newsfeed> news;
    public RecyclerViewAdapterProfile(){
        this.news = new ArrayList<>();
    }
    public void setNews(List<Newsfeed> news){
        this.news = news;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public RecyclerViewProfileItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsfeedBinding binding = ItemNewsfeedBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new RecyclerViewProfileItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProfileItemViewHolder holder, int position) {
        holder.binding.postText.setText(news.get(position).getPostText());
        holder.binding.postImage.setImageResource(news.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class RecyclerViewProfileItemViewHolder extends RecyclerView.ViewHolder {
        public ItemNewsfeedBinding binding;

        public RecyclerViewProfileItemViewHolder(@NonNull View itemView) {
            super(itemView);
            
            binding = ItemNewsfeedBinding.bind(itemView);
        }
    }
}
