package com.example.learnandearnv01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrdersActitvity extends AppCompatActivity {

    NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        BottomNavigationView bottomNavigationView = findViewById(R.id.myOrdersBottomNavigationView);
        navController = Navigation.findNavController(this,R.id.myOrdersFragmentContainerView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }
}