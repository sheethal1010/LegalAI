package com.legalai.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.models.OnboardingPage;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    private final List<OnboardingPage> pages;

    public OnboardingAdapter(List<OnboardingPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onboarding_page, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        OnboardingPage page = pages.get(position);
        h.tvEmoji.setText(page.getEmoji());
        h.tvTitle.setText(page.getTitle());
        h.tvSubtitle.setText(page.getSubtitle());
    }

    @Override
    public int getItemCount() { return pages.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmoji, tvTitle, tvSubtitle;

        ViewHolder(View v) {
            super(v);
            tvEmoji = v.findViewById(R.id.tv_ob_emoji);
            tvTitle = v.findViewById(R.id.tv_ob_title);
            tvSubtitle = v.findViewById(R.id.tv_ob_subtitle);
        }
    }
}
