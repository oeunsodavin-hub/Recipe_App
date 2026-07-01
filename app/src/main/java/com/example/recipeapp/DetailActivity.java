package com.example.recipeapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetailRecipe;
    private TextView tvDetailTitle, tvDetailInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ត្រូវប្រាកដថាឈ្មោះ Layout នេះត្រូវនឹងឈ្មោះឯកសារក្នុង folder layout របស់បងប្អូន
        setContentView(R.layout.fragment_recipe_detail);


        // ១. តភ្ជាប់ ID (សូមពិនិត្យមើល ID ក្នុង fragment_recipe_detail.xml ឱ្យត្រូវតាមនេះ)
        imgDetailRecipe = findViewById(R.id.imgDetailRecipe);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailInstructions = findViewById(R.id.tvDetailInstructions);

        // ២. ទាញទិន្នន័យដែលផ្ញើមកពី HomeFragment
        String title = getIntent().getStringExtra("RECIPE_NAME");
        String imageUrl = getIntent().getStringExtra("RECIPE_IMAGE");
        String instructions = getIntent().getStringExtra("RECIPE_INSTRUCTIONS");

        // ៣. បង្ហាញទិន្នន័យ
        tvDetailTitle.setText(title);
        tvDetailInstructions.setText(instructions);

        // ៤. ប្រើ Glide ដើម្បីផ្ទុករូបភាពពី URL
        Glide.with(this)
                .load(imageUrl)
                .into(imgDetailRecipe);
    }
}