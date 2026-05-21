package com.legalai.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.activities.CaseTrackingActivity;
import com.legalai.app.adapters.CaseAdapter;
import com.legalai.app.models.LegalCase;
import com.legalai.app.utils.FirebaseHelper;
import com.legalai.app.utils.MockDataGenerator;

import java.util.List;

public class CasesFragment extends Fragment {

    private RecyclerView rv;
    private View emptyState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cases, container, false);
        rv = view.findViewById(R.id.rv_cases);
        emptyState = view.findViewById(R.id.empty_state);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadCases();
        return view;
    }

    private void loadCases() {
        new FirebaseHelper().getCases(cases -> {
            if (cases.isEmpty()) cases = MockDataGenerator.getMockCases();
            showCases(cases);
        });
    }

    private void showCases(List<LegalCase> cases) {
        boolean empty = cases.isEmpty();
        rv.setVisibility(empty ? View.GONE : View.VISIBLE);
        emptyState.setVisibility(empty ? View.VISIBLE : View.GONE);
        rv.setAdapter(new CaseAdapter(requireContext(), cases, c -> {
            Intent intent = new Intent(requireActivity(), CaseTrackingActivity.class);
            intent.putExtra("case_title", c.getTitle());
            intent.putExtra("case_type", c.getType());
            intent.putExtra("case_description", c.getDescription());
            intent.putExtra("case_agent", c.getAgentUsed());
            intent.putExtra("case_lawyer", c.getAssignedLawyer());
            intent.putExtra("case_status", c.getStatus());
            startActivity(intent);
        }));
    }
}
