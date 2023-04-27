package com.example.cooktogether.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooktogether.data.models.Person;
import com.example.cooktogether.databinding.ItemPersonBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterChat extends RecyclerView.Adapter<RecyclerViewAdapterChat.RecyclerViewItemViewHolder> {
    
    List<Person> contacts;
    public RecyclerViewAdapterChat(){
        this.contacts = new ArrayList<>();
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapterChat.RecyclerViewItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPersonBinding binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.getContext()));
        
        return new RecyclerViewItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterChat.RecyclerViewItemViewHolder holder, int position) {
        holder.binding.nameText.setText(contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    class RecyclerViewItemViewHolder extends RecyclerView.ViewHolder {
        public ItemPersonBinding binding;

        public RecyclerViewItemViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemPersonBinding.bind(itemView);
        }
    }
}
