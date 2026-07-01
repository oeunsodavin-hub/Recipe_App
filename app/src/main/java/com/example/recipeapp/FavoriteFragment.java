package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private RecyclerView rvFavorite;
    private FavoriteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = view.findViewById(R.id.rvFavorite);
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));

        // ទាញទិន្នន័យពី Database
        List<RecipeEntity> savedRecipes = AppDatabase.getInstance(requireContext()).recipeDao().getAllSavedRecipes();

        if (savedRecipes != null) {
            adapter = new FavoriteAdapter(getContext(), savedRecipes, recipe -> {
                // កូដសម្រាប់ពេលចុចលើម្ហូប (DetailActivity)
            });
            rvFavorite.setAdapter(adapter);
        }
    }
}