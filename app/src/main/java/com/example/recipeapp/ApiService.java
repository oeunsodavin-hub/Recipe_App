package com.example.recipeapp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.Map; // អ្នកអាចប្រើ Response model ផ្ទាល់ខ្លួនរបស់អ្នកនៅទីនេះ

public interface ApiService {
    // 1. បង្ហាញបញ្ជីមុខម្ហូបតាមប្រភេទ (Categories)

    // ក្នុង ApiService.java
    @GET("search.php")
    Call<RecipeResponse> searchRecipes(@Query("s") String query);
    @GET("categories.php")
    Call<Map<String, Object>> getCategories();

    // 2. បង្ហាញបញ្ជីម្ហូបតាម Category
    @GET("filter.php")
    Call<Map<String, Object>> getRecipesByCategory(@Query("c") String categoryName);

    // 3. បង្ហាញព័ត៌មានលម្អិតនៃម្ហូប
    @GET("lookup.php")
    Call<Map<String, Object>> getRecipeDetails(@Query("i") String id);
}