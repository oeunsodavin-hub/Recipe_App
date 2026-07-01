package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import androidx.recyclerview.widget.GridLayoutManager;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ភ្ជាប់ទៅនឹង Layout fragment_home
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvAllRecipes);

        // កំណត់ LayoutManager ឱ្យបង្ហាញបញ្ឈរ (ងាយស្រួលបំផុត)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // បង្កើតបញ្ជីម្ហូបដោយដៃ (Static Data)
        List<Recipe> myRecipes = new ArrayList<>();
        myRecipes.add(new Recipe("Chicken Handi", "Main Course", "https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg"));
        myRecipes.add(new Recipe("Beef Bourguignon", "French", "https://www.themealdb.com/images/media/meals/vtqxfu1511798497.jpg"));
        myRecipes.add(new Recipe("Pizza", "Italian", "https://www.themealdb.com/images/media/meals/x0lk931587671540.jpg"));
        myRecipes.add(new Recipe("Sushi", "Japanese", "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"));

        // បញ្ជូនទៅកាន់ Adapter
        recipeAdapter = new RecipeAdapter(getContext(), myRecipes);
        recyclerView.setAdapter(recipeAdapter);
    }

    private void fetchRecipes(String query) {
        RetrofitClient.getApiService().searchRecipes(query).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null) {
                    // បញ្ជូន List ទៅកាន់ Adapter
                    recipeAdapter.setRecipeList(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}