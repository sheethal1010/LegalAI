package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.legalai.app.R;
import com.legalai.app.adapters.LawyerAdapter;
import com.legalai.app.models.Lawyer;
import com.legalai.app.utils.FirebaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SavedLawyersActivity extends AppCompatActivity {

    private RecyclerView rvSavedLawyers;
    private LinearLayout emptyState;
    private LawyerAdapter adapter;
    private final List<Lawyer> savedLawyers = new ArrayList<>();
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lawyers);
        firebaseHelper = new FirebaseHelper();

        ImageView ivBack = findViewById(R.id.iv_back);
        rvSavedLawyers = findViewById(R.id.rv_saved_lawyers);
        emptyState = findViewById(R.id.empty_state);

        ivBack.setOnClickListener(v -> onBackPressed());

        adapter = new LawyerAdapter(this, savedLawyers, lawyer -> {
            Intent intent = new Intent(this, LawyerProfileActivity.class);
            intent.putExtra("lawyer_id", lawyer.getId());
            intent.putExtra("lawyer_name", lawyer.getName());
            intent.putExtra("lawyer_specialty", lawyer.getSpecialty());
            intent.putExtra("lawyer_rating", lawyer.getRating());
            intent.putExtra("lawyer_experience", lawyer.getExperienceYears());
            intent.putExtra("lawyer_fee", lawyer.getConsultationFee());
            intent.putExtra("lawyer_bio", lawyer.getBio());
            intent.putExtra("lawyer_location", lawyer.getLocation());
            intent.putExtra("lawyer_available", lawyer.isAvailable());
            intent.putExtra("lawyer_review_count", lawyer.getReviewCount());
            intent.putExtra("lawyer_initials", lawyer.getAvatarInitials());
            startActivity(intent);
        });
        rvSavedLawyers.setLayoutManager(new LinearLayoutManager(this));
        rvSavedLawyers.setAdapter(adapter);
        loadSavedLawyers();
    }

    private void loadSavedLawyers() {
        DatabaseReference ref = firebaseHelper.getSavedLawyersRef();
        if (ref == null) { showEmpty(); return; }
        ref.get().addOnSuccessListener(snapshot -> {
            savedLawyers.clear();
            if (snapshot.exists()) {
                for (com.google.firebase.database.DataSnapshot child : snapshot.getChildren()) {
                    Lawyer lawyer = child.getValue(Lawyer.class);
                    if (lawyer != null) savedLawyers.add(lawyer);
                }
            }
            adapter.notifyDataSetChanged();
            boolean empty = savedLawyers.isEmpty();
            rvSavedLawyers.setVisibility(empty ? View.GONE : View.VISIBLE);
            emptyState.setVisibility(empty ? View.VISIBLE : View.GONE);
        }).addOnFailureListener(e -> showEmpty());
    }

    private void showEmpty() {
        rvSavedLawyers.setVisibility(View.GONE);
        emptyState.setVisibility(View.VISIBLE);
    }
}
