package com.example.fragmentsdesign.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmentsdesign.R;
import com.example.fragmentsdesign.databinding.ActivityMainBinding;
import com.example.fragmentsdesign.ui.fragment.AccountFragment;
import com.example.fragmentsdesign.ui.fragment.ChartFragment;
import com.example.fragmentsdesign.ui.fragment.CoursesFragment;
import com.example.fragmentsdesign.ui.fragment.HomeFragment;
import com.example.fragmentsdesign.ui.fragment.MoreFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //show fragment
        showFragments(new HomeFragment());

        //setup listeners
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.id_homeFragment){
                showFragments(new HomeFragment());
            }else if(item.getItemId() == R.id.id_coursesFragment){
                showFragments(new CoursesFragment());
            }else if(item.getItemId() == R.id.id_chartFragment){
                showFragments(new ChartFragment());
            }else if(item.getItemId() == R.id.id_accountFragments){
                showFragments(new AccountFragment());
            }else if(item.getItemId() == R.id.id_moreFragment){
                showFragments(new MoreFragment());
            }
            return true;
        });
    }

//    show fragment in main Activity
    private void showFragments(Fragment fragment){
        //fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //replace fragment in lytFragment View
        fragmentTransaction.replace(R.id.lytFragments,fragment);

        //commit fragment
        fragmentTransaction.commit();
    }
}