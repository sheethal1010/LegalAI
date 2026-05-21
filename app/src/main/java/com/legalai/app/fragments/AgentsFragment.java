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
import com.legalai.app.activities.ChatActivity;
import com.legalai.app.adapters.AgentAdapter;
import com.legalai.app.utils.MockDataGenerator;

public class AgentsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agents, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_agents);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(new AgentAdapter(requireContext(), MockDataGenerator.getAgents(), agent -> {
            Intent intent = new Intent(requireActivity(), ChatActivity.class);
            intent.putExtra("agent_name", agent.getTitle());
            intent.putExtra("agent_description", agent.getDescription());
            startActivity(intent);
        }));
        return view;
    }
}
