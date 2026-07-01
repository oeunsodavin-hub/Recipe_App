package com.example.recipeapp;

import com.google.gson.annotations.SerializedName;

public class Recipe {

    public String idMeal;

    @SerializedName("strMeal") // ឈ្មោះក្នុង API
    private String title;      // ឈ្មោះក្នុង App យើង

    @SerializedName("strCategory")
    private String category;

    @SerializedName("strMealThumb")
    private String imageUrl;

    @SerializedName("strInstructions")
    private String instructions;

    public String getInstructions() { return instructions; }

    // Constructor
    public Recipe() {}

    public Recipe(String title, String category, String imageUrl) {
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getter និង Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}