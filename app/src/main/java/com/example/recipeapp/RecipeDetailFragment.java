package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class RecipeDetailFragment extends Fragment {

    private ImageView imgDetailRecipe;
    // បានបន្ថែម tvDetailInstructions នៅទីនេះ
    private TextView tvDetailTitle, tvDetailCategory, tvDetailInstructions;
    private FloatingActionButton fabFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ១. កំណត់អត្តសញ្ញាណ View
        imgDetailRecipe = view.findViewById(R.id.imgDetailRecipe);
        tvDetailTitle = view.findViewById(R.id.tvDetailTitle);
        tvDetailCategory = view.findViewById(R.id.tvDetailCategory);
        tvDetailInstructions = view.findViewById(R.id.tvDetailInstructions); // ភ្ជាប់ ID
        fabFavorite = view.findViewById(R.id.fabFavorite);

        // ២. ទទួលទិន្នន័យពី Bundle
        if (getArguments() != null) {
            String title = getArguments().getString("recipeTitle");
            String category = getArguments().getString("recipeCategory");
            String imageUrl = getArguments().getString("recipeImage");
            String instructions = getArguments().getString("recipeInstructions"); // ទទួល Instructions

            // ៣. បង្ហាញទិន្នន័យលើអេក្រង់
            tvDetailTitle.setText(title);
            tvDetailCategory.setText(category);
            tvDetailInstructions.setText(instructions); // បង្ហាញ Instructions ពិតប្រាកដ

            Glide.with(requireContext()).load(imageUrl).into(imgDetailRecipe);

            // ហៅមុខងារពិនិត្យពណ៌ផ្កាយ
            checkIfFavorite(title);

            // ៤. Logic សម្រាប់ Save ឬ Delete
            fabFavorite.setOnClickListener(v -> {
                List<RecipeEntity> savedList = AppDatabase.getInstance(requireContext()).recipeDao().getAllSavedRecipes();
                RecipeEntity existingRecipe = null;

                for (RecipeEntity item : savedList) {
                    if (item.getTitle() != null && item.getTitle().equals(title)) {
                        existingRecipe = item;
                        break;
                    }
                }

                if (existingRecipe != null) {
                    AppDatabase.getInstance(requireContext()).recipeDao().deleteRecipe(existingRecipe);
                    Toast.makeText(getContext(), "បានលុបចេញពី Favorite!", Toast.LENGTH_SHORT).show();
                    fabFavorite.setImageResource(android.R.drawable.btn_star_big_off);
                } else {
                    RecipeEntity newRecipe = new RecipeEntity();
                    newRecipe.setTitle(title);
                    newRecipe.setCategory(category);
                    newRecipe.setImageUrl(imageUrl);

                    AppDatabase.getInstance(requireContext()).recipeDao().insertRecipe(newRecipe);
                    Toast.makeText(getContext(), "បានរក្សាទុក!", Toast.LENGTH_SHORT).show();
                    fabFavorite.setImageResource(android.R.drawable.btn_star_big_on);
                }
            });
        }
    }

    private void checkIfFavorite(String title) {
        // ប្រើ Executor ដើម្បីមិនឱ្យគាំង Main Thread
        new Thread(() -> {
            // 1. សាកល្បងអានទិន្នន័យក្នុង Background Thread
            List<RecipeEntity> savedList = AppDatabase.getInstance(requireContext()).recipeDao().getAllSavedRecipes();

            // 2. ពិនិត្យមើលថាតើ title នេះមានក្នុង list ឬអត់
            boolean isSaved = false;
            for (RecipeEntity item : savedList) {
                if (item.getTitle() != null && item.getTitle().equals(title)) {
                    isSaved = true;
                    break;
                }
            }

            // 3. ត្រឡប់មក Main Thread ដើម្បី Update UI
            boolean finalIsSaved = isSaved;
            requireActivity().runOnUiThread(() -> {
                if (finalIsSaved) {
                    fabFavorite.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    fabFavorite.setImageResource(android.R.drawable.btn_star_big_off);
                }
            });
        }).start();
    }
}