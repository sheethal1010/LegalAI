package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.legalai.app.R;
import com.legalai.app.adapters.OnboardingAdapter;
import com.legalai.app.models.OnboardingPage;
import com.legalai.app.utils.AppUtils;
import com.legalai.app.utils.MockDataGenerator;

import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotsContainer;
    private Button btnNext, btnSkip;
    private List<OnboardingPage> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.view_pager);
        dotsContainer = findViewById(R.id.dots_container);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        pages = MockDataGenerator.getOnboardingPages();
        viewPager.setAdapter(new OnboardingAdapter(pages));

        setupDots(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setupDots(position);
                boolean last = position == pages.size() - 1;
                btnNext.setText(last ? "Get Started" : "Next");
                btnSkip.setVisibility(last ? View.GONE : View.VISIBLE);
            }
        });

        btnNext.setOnClickListener(v -> {
            int current = viewPager.getCurrentItem();
            if (current < pages.size() - 1) {
                viewPager.setCurrentItem(current + 1);
            } else {
                goToLogin();
            }
        });

        btnSkip.setOnClickListener(v -> goToLogin());
    }

    private void setupDots(int currentPage) {
        dotsContainer.removeAllViews();
        for (int i = 0; i < pages.size(); i++) {
            ImageView dot = new ImageView(this);
            dot.setImageResource(i == currentPage
                    ? R.drawable.ic_dot_active
                    : R.drawable.ic_dot_inactive);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            params.setMargins(6, 0, 6, 0);
            dot.setLayoutParams(params);
            dotsContainer.addView(dot);
        }
    }

    private void goToLogin() {
        AppUtils.setOnboardingDone(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
