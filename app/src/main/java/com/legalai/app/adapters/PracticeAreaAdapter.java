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
import com.legalai.app.models.PracticeArea;

import java.util.List;

public class PracticeAreaAdapter extends RecyclerView.Adapter<PracticeAreaAdapter.ViewHolder> {

    public interface OnAreaClickListener {
        void onAreaClick(PracticeArea area);
    }

    private final Context context;
    private final List<PracticeArea> areas;
    private final OnAreaClickListener listener;

    public PracticeAreaAdapter(Context context, List<PracticeArea> areas, OnAreaClickListener listener) {
        this.context = context;
        this.areas = areas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_practice_area, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        PracticeArea area = areas.get(position);
        h.tvEmoji.setText(area.getEmoji());
        h.tvTitle.setText(area.getTitle());
        h.tvCount.setText(area.getLawyerCount() + " lawyers");
        h.card.setOnClickListener(v -> listener.onAreaClick(area));
    }

    @Override
    public int getItemCount() { return areas.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView tvEmoji, tvTitle, tvCount;

        ViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card_area);
            tvEmoji = v.findViewById(R.id.tv_area_emoji);
            tvTitle = v.findViewById(R.id.tv_area_title);
            tvCount = v.findViewById(R.id.tv_area_count);
        }
    }
}
