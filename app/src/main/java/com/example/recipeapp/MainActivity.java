package com.example.recipeapp;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 💡 ភ្ជាប់ទៅកាន់ Toolbar ដែលយើងទើបថែមក្នុង XML
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // 💡 កំណត់ថាផ្ទាំងណាខ្លះជាផ្ទាំងធំ (មិនបង្ហាញប៊ូតុង Back ទេ)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.welcomeFragment, R.id.loginFragment, R.id.registerFragment, R.id.homeFragment, R.id.favoriteFragment)
                .build();

        // ភ្ជាប់ Toolbar និង BottomNav ទៅកាន់ Navigation ឱ្យដើរស្វ័យប្រវត្ត
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

        // តាមដានលាក់/បង្ហាញ Bottom Navigation និង Toolbar
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination,
                                             @Nullable Bundle arguments) {
                int destId = destination.getId();

                if (destId == R.id.welcomeFragment || destId == R.id.loginFragment || destId == R.id.registerFragment) {
                    bottomNav.setVisibility(View.GONE);
                    toolbar.setVisibility(View.GONE); // លាក់របារខាងលើនៅផ្ទាំង Auth
                } else {
                    bottomNav.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE); // បង្ហាញរបារខាងលើនៅផ្ទាំងផ្សេងៗ
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}