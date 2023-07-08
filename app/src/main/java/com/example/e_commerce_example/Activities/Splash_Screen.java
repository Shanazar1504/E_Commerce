package com.example.e_commerce_example.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.e_commerce_example.R;

public class Splash_Screen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(Splash_Screen.this, Main_Page.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
    }
}