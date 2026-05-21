package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.adapters.LawyerAdapter;
import com.legalai.app.models.Lawyer;
import com.legalai.app.utils.MockDataGenerator;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String query = getIntent().getStringExtra("query");
        String specialty = getIntent().getStringExtra("specialty");

        ImageView ivBack = findViewById(R.id.iv_back);
        TextView tvTitle = findViewById(R.id.tv_title);
        RecyclerView rv = findViewById(R.id.rv_results);

        ivBack.setOnClickListener(v -> onBackPressed());

        String filterKey = specialty != null ? specialty : query;
        tvTitle.setText(filterKey != null ? filterKey : "Search Results");

        List<Lawyer> all = MockDataGenerator.getLawyers();
        List<Lawyer> filtered = new ArrayList<>();
        if (filterKey != null) {
            for (Lawyer l : all) {
                if ((l.getSpecialty() != null && l.getSpecialty().toLowerCase().contains(filterKey.toLowerCase()))
                        || (l.getName() != null && l.getName().toLowerCase().contains(filterKey.toLowerCase()))) {
                    filtered.add(l);
                }
            }
        } else {
            filtered = all;
        }

        LawyerAdapter adapter = new LawyerAdapter(this, filtered, lawyer -> {
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
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
