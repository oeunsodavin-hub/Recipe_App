package com.example.recipeapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 💡 ប្រកាសថា Class នេះជា Database ដែលមានផ្ទុកតារាង RecipeEntity (Version 1)
@Database(entities = {RecipeEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "recipe_db")
                    .allowMainThreadQueries() // ប្រើតែពេលតេស្ត! ក្នុងផលិតផលពិតត្រូវប្រើ Background Thread
                    .build();
        }
        return instance;
    }
}