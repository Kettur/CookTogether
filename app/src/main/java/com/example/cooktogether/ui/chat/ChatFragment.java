package com.example.cooktogether.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cooktogether.data.repositories.PersonRepository;
import com.example.cooktogether.databinding.FragmentChatBinding;
import com.example.cooktogether.ui.adapters.RecyclerViewAdapterChat;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerView.setAdapter(new RecyclerViewAdapterChat());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ((RecyclerViewAdapterChat) binding.recyclerView.getAdapter()).setContacts(new PersonRepository().getData());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}