package com.example.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ១. ចាប់យក Component ពី XML ឱ្យចំឈ្មោះ ID ថ្មី
        EditText etPassword = view.findViewById(R.id.etPassword);
        Button btnSignInWithPassword = view.findViewById(R.id.btnSignInWithPassword);
        Button btnLoginWithEmail = view.findViewById(R.id.btnLoginWithEmail);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        // ១. ប៊ូតុង Sign In (វាយតែ Password រួចទៅកាន់ Home)
        btnSignInWithPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString().trim();
                if (password.isEmpty()) {
                    Toast.makeText(getContext(), "Please complete password", Toast.LENGTH_SHORT).show();
                } else {
                    Navigation.findNavController(v).navigate(R.id.action_welcome_to_home);
                }
            }
        });

// ២. ប៊ូតុង Login with Email (រត់ទៅកាន់ផ្ទាំង Login ថ្មីដាច់ដោយឡែក)
        btnLoginWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_welcome_to_login);
            }
        });

// ៣. ប៊ូតុង Register (រត់ទៅកាន់ផ្ទាំងបង្កើតគណនី)
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_welcome_to_register);
            }
        });
    }
}