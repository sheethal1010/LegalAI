package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.legalai.app.R;
import com.legalai.app.utils.AppUtils;
import com.legalai.app.utils.FirebaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            FirebaseHelper fb = new FirebaseHelper();
            if (fb.isLoggedIn()) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (AppUtils.isOnboardingDone(this)) {
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                startActivity(new Intent(this, OnboardingActivity.class));
            }
            finish();
        }, 2000);
    }
}
