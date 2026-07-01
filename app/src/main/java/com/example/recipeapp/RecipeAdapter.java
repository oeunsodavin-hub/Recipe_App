package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.tvRecipeTitle.setText(recipe.getTitle());

        // បងប្អូនអាចបន្ថែម Category បើចង់បង្ហាញ៖
        // holder.tvRecipeCategory.setText(recipe.getCategory());

        Glide.with(context)
                .load(recipe.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background) // កុំប្រើពណ៌បៃតងសាកល្បងច្រើនពេក
                .error(R.drawable.ic_launcher_background)
                .transition(DrawableTransitionOptions.withCrossFade()) // បង្ហាញរូបភាពរលូន
                .diskCacheStrategy(DiskCacheStrategy.ALL) // ចងចាំរូបភាពក្នុងម៉ាស៊ីន
                .centerCrop()
                .into(holder.imgRecipe);
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }

    // មានតែ ViewHolder មួយនេះគត់!
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView tvRecipeTitle;
        ImageView imgRecipe;
        // បើបងប្អូនប្រើ tvRecipeCategory ក្នុង item_recipe.xml សូមដាក់វាចូលមកវិញ
        TextView tvRecipeCategory;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitle);
            imgRecipe = itemView.findViewById(R.id.imgRecipe);
            // កុំភ្លេចកែ ID ឱ្យត្រូវក្នុង item_recipe.xml
            tvRecipeCategory = itemView.findViewById(R.id.tvRecipeCategory);
        }
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }
}