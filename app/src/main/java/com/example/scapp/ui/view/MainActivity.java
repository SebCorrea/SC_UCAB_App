package com.example.scapp.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.scapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{


    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(binding.initialFragment.getId(), InitialFragment.class, null)
                    .commit();
        }

    }
}