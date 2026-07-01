package com.example.recipeapp;
import java.util.List;

public class RecipeResponse {
    private List<Recipe> meals; // ប្រើ private សម្រាប់ Encapsulation

    // បន្ថែម Getter method នេះដើម្បីឱ្យ Fragment អាចអានបាន
    public List<Recipe> getMeals() {
        return meals;
    }

    public void setMeals(List<Recipe> meals) {
        this.meals = meals;
    }
}