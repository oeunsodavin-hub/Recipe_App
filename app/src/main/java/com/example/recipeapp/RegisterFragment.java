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

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ភ្ជាប់ទៅកាន់ឯកសារប្លង់ fragment_register.xml
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ចាប់យកប៊ូតុង Sign Up ពី XML
        Button btnRegisterSubmit = view.findViewById(R.id.btnRegisterSubmit);
        EditText etRegEmail = view.findViewById(R.id.etRegEmail);
        EditText etRegPassword = view.findViewById(R.id.etRegPassword);

        // កំណត់ព្រឹត្តិការណ៍ចុចប៊ូតុង
        btnRegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etRegEmail.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                    // ហៅប្រព័ន្ធ Navigation ឱ្យរត់ទៅកាន់ផ្ទាំង Home
                    Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_homeFragment);
                }
            }
        });
    }
}