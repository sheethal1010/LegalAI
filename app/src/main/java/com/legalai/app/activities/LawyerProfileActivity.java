package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.legalai.app.R;
import com.legalai.app.utils.FirebaseHelper;

public class LawyerProfileActivity extends AppCompatActivity {

    private ImageView ivBack, ivBookmark;
    private TextView tvInitials, tvLawyerName, tvSpecialty, tvRating, tvReviewCount;
    private TextView tvExperience, tvFee, tvLocation, tvBio, tvAvailability;
    private MaterialButton btnBook;
    private FirebaseHelper firebaseHelper;
    private boolean isBookmarked = false;
    private String lawyerId, lawyerName, lawyerSpecialty, lawyerFee, lawyerBio, lawyerLocation, lawyerInitials;
    private double lawyerRating;
    private int lawyerExperience, lawyerReviewCount;
    private boolean lawyerAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_profile);
        firebaseHelper = new FirebaseHelper();
        extractIntentData();
        bindViews();
        populateViews();
        setupListeners();
        checkIfBookmarked();
    }

    private void extractIntentData() {
        Intent i = getIntent();
        lawyerId = i.getStringExtra("lawyer_id");
        lawyerName = i.getStringExtra("lawyer_name");
        lawyerSpecialty = i.getStringExtra("lawyer_specialty");
        lawyerRating = i.getDoubleExtra("lawyer_rating", 0.0);
        lawyerExperience = i.getIntExtra("lawyer_experience", 0);
        lawyerFee = i.getStringExtra("lawyer_fee");
        lawyerBio = i.getStringExtra("lawyer_bio");
        lawyerLocation = i.getStringExtra("lawyer_location");
        lawyerAvailable = i.getBooleanExtra("lawyer_available", false);
        lawyerReviewCount = i.getIntExtra("lawyer_review_count", 0);
        lawyerInitials = i.getStringExtra("lawyer_initials");
    }

    private void bindViews() {
        ivBack = findViewById(R.id.iv_back);
        ivBookmark = findViewById(R.id.iv_bookmark);
        tvInitials = findViewById(R.id.tv_initials);
        tvLawyerName = findViewById(R.id.tv_lawyer_name);
        tvSpecialty = findViewById(R.id.tv_specialty);
        tvRating = findViewById(R.id.tv_rating);
        tvReviewCount = findViewById(R.id.tv_review_count);
        tvExperience = findViewById(R.id.tv_experience);
        tvFee = findViewById(R.id.tv_fee);
        tvLocation = findViewById(R.id.tv_location);
        tvBio = findViewById(R.id.tv_bio);
        tvAvailability = findViewById(R.id.tv_availability);
        btnBook = findViewById(R.id.btn_book);
    }

    private void populateViews() {
        tvInitials.setText(lawyerInitials != null ? lawyerInitials : "??");
        tvLawyerName.setText(lawyerName);
        tvSpecialty.setText(lawyerSpecialty);
        tvRating.setText(String.valueOf(lawyerRating));
        tvReviewCount.setText("(" + lawyerReviewCount + " reviews)");
        tvExperience.setText(lawyerExperience + " yrs");
        tvFee.setText(lawyerFee);
        tvLocation.setText(lawyerLocation);
        tvBio.setText(lawyerBio);
        tvAvailability.setText(lawyerAvailable ? "Available" : "Busy");
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> onBackPressed());
        ivBookmark.setOnClickListener(v -> toggleBookmark());
        btnBook.setOnClickListener(v ->
                Toast.makeText(this, "Booking " + lawyerName + "…", Toast.LENGTH_SHORT).show());
    }

    private void checkIfBookmarked() {
        if (lawyerId == null) return;
        firebaseHelper.isLawyerSaved(lawyerId, saved -> {
            isBookmarked = saved;
            updateBookmarkIcon();
        });
    }

    private void toggleBookmark() {
        if (isBookmarked) {
            firebaseHelper.removeSavedLawyer(lawyerId, success -> {
                if (success) { isBookmarked = false; updateBookmarkIcon();
                    Toast.makeText(this, "Removed from saved", Toast.LENGTH_SHORT).show(); }
            });
        } else {
            firebaseHelper.saveLawyer(lawyerId, lawyerName, lawyerSpecialty, lawyerInitials, success -> {
                if (success) { isBookmarked = true; updateBookmarkIcon();
                    Toast.makeText(this, "Lawyer saved!", Toast.LENGTH_SHORT).show(); }
            });
        }
    }

    private void updateBookmarkIcon() {
        ivBookmark.setImageResource(isBookmarked ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
    }
}
