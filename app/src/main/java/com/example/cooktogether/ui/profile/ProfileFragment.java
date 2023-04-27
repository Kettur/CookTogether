package com.example.cooktogether.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cooktogether.R;
import com.example.cooktogether.data.repositories.NewsRepository;
import com.example.cooktogether.databinding.FragmentProfileBinding;
import com.example.cooktogether.ui.MainActivity;
import com.example.cooktogether.ui.adapters.RecyclerViewAdapterProfile;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.nameProfile.setText("PlaceHolder");
        binding.photoProfile.setImageResource(R.drawable.ic_profile_black_24dp);
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new RecyclerViewAdapterProfile());
        ((RecyclerViewAdapterProfile) binding.recyclerView.getAdapter()).setNews(new NewsRepository().getData());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}