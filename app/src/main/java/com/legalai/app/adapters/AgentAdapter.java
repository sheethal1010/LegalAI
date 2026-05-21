package com.legalai.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.legalai.app.R;
import com.legalai.app.models.AiAgent;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.ViewHolder> {

    public interface OnAgentClickListener {
        void onAgentClick(AiAgent agent);
    }

    private final Context context;
    private final List<AiAgent> agents;
    private final OnAgentClickListener listener;

    public AgentAdapter(Context context, List<AiAgent> agents, OnAgentClickListener listener) {
        this.context = context;
        this.agents = agents;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_agent_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        AiAgent agent = agents.get(position);
        h.tvEmoji.setText(agent.getEmoji());
        h.tvTitle.setText(agent.getTitle());
        h.tvCategory.setText(agent.getCategory());
        h.tvDescription.setText(agent.getDescription());
        h.tvRating.setText(String.valueOf(agent.getRating()));
        h.tvUsage.setText(agent.getUsageCount() + " uses");
        h.card.setOnClickListener(v -> listener.onAgentClick(agent));
        h.btnUse.setOnClickListener(v -> listener.onAgentClick(agent));
    }

    @Override
    public int getItemCount() { return agents.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView tvEmoji, tvTitle, tvCategory, tvDescription, tvRating, tvUsage;
        MaterialButton btnUse;

        ViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card_agent);
            tvEmoji = v.findViewById(R.id.tv_agent_emoji);
            tvTitle = v.findViewById(R.id.tv_agent_title);
            tvCategory = v.findViewById(R.id.tv_agent_category);
            tvDescription = v.findViewById(R.id.tv_agent_description);
            tvRating = v.findViewById(R.id.tv_agent_rating);
            tvUsage = v.findViewById(R.id.tv_agent_usage);
            btnUse = itemView.findViewById(R.id.btn_use_agent);
        }
    }
}
