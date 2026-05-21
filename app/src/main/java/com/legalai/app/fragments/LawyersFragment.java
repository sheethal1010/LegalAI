package com.legalai.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.activities.LawyerProfileActivity;
import com.legalai.app.adapters.LawyerAdapter;
import com.legalai.app.utils.MockDataGenerator;

public class LawyersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lawyers, container, false);

        RecyclerView rv = view.findViewById(R.id.rv_lawyers);
        EditText etSearch = view.findViewById(R.id.et_search_lawyers);

        LawyerAdapter adapter = new LawyerAdapter(requireContext(), MockDataGenerator.getLawyers(), lawyer -> {
            Intent intent = new Intent(requireActivity(), LawyerProfileActivity.class);
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

        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(adapter);

        etSearch.addTextChangedListener(new android.text.TextWatcher() {
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            public void onTextChanged(CharSequence s, int st, int b, int c) { adapter.filter(s.toString()); }
            public void afterTextChanged(android.text.Editable s) {}
        });

        return view;
    }
}
