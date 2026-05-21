package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.adapters.AgentAdapter;
import com.legalai.app.utils.MockDataGenerator;

public class AgentMarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_marketplace);

        ImageView ivBack = findViewById(R.id.iv_back);
        RecyclerView rv = findViewById(R.id.rv_agents);
        ivBack.setOnClickListener(v -> onBackPressed());

        AgentAdapter adapter = new AgentAdapter(this, MockDataGenerator.getAgents(), agent -> {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("agent_name", agent.getTitle());
            intent.putExtra("agent_description", agent.getDescription());
            startActivity(intent);
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
