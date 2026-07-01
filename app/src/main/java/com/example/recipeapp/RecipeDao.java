package com.example.recipeapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy; // បន្ថែមបន្ទាត់នេះ!
import androidx.room.Query;
import java.util.List;


@Dao
public interface RecipeDao {
    @Query("SELECT * FROM favorites")
    List<RecipeEntity> getAllSavedRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeEntity recipe);

    @Delete
    void deleteRecipe(RecipeEntity recipe);
}