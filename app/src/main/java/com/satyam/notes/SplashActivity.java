package com.satyam.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.satyam.notes.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.appNameTextView.animate().scaleX(2).scaleY(2).rotationX(1*360).setDuration(2000).start();
        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    Log.e("splash handler", ignored.getMessage());
                }
            }
        }, 5000);
    }
}