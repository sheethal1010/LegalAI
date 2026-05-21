package com.legalai.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.legalai.app.R;
import com.legalai.app.models.LegalCase;
import com.legalai.app.utils.AppUtils;

import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {

    public interface OnCaseClickListener {
        void onCaseClick(LegalCase legalCase);
    }

    private final Context context;
    private final List<LegalCase> cases;
    private final OnCaseClickListener listener;

    public CaseAdapter(Context context, List<LegalCase> cases, OnCaseClickListener listener) {
        this.context = context;
        this.cases = cases;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_case_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        LegalCase c = cases.get(position);
        h.tvTitle.setText(c.getTitle());
        h.tvType.setText(c.getType());
        h.tvStatus.setText(c.getStatusLabel());
        h.tvTime.setText(AppUtils.formatTimestamp(c.getTimestamp()));
        if (c.getAgentUsed() != null) {
            h.tvAgent.setText(c.getAgentUsed());
            h.tvAgent.setVisibility(View.VISIBLE);
        } else {
            h.tvAgent.setVisibility(View.GONE);
        }
        h.card.setOnClickListener(v -> listener.onCaseClick(c));
    }

    @Override
    public int getItemCount() { return cases.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView tvTitle, tvType, tvStatus, tvTime, tvAgent;

        ViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card_case);
            tvTitle = v.findViewById(R.id.tv_case_title);
            tvType = v.findViewById(R.id.tv_case_type);
            tvStatus = v.findViewById(R.id.tv_case_status);
            tvTime = v.findViewById(R.id.tv_case_time);
            tvAgent = v.findViewById(R.id.tv_case_agent);
        }
    }
}
