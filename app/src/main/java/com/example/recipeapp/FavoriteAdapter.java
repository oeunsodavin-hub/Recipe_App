package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private List<RecipeEntity> favoriteList;
    // 💡 បន្ថែម Listener មួយសម្រាប់ផ្ញើព្រឹត្តិការណ៍ចុចទៅ Fragment
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(RecipeEntity recipe);
    }

    public FavoriteAdapter(Context context, List<RecipeEntity> favoriteList, OnItemClickListener listener) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        RecipeEntity recipe = favoriteList.get(position);

        holder.tvTitle.setText(recipe.getTitle());
        holder.tvCategory.setText(recipe.getCategory());

        Glide.with(context)
                .load(recipe.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgRecipe);

        // 💡 ចាប់ព្រឹត្តិការណ៍ចុចលើ Item ទាំងមូល
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(recipe);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecipe;
        TextView tvTitle;
        TextView tvCategory;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecipe = itemView.findViewById(R.id.imgRecipe);
            tvTitle = itemView.findViewById(R.id.tvRecipeTitle);
            tvCategory = itemView.findViewById(R.id.tvRecipeCategory);
        }
    }
}