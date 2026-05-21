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
import com.legalai.app.models.Lawyer;

import java.util.ArrayList;
import java.util.List;

public class LawyerAdapter extends RecyclerView.Adapter<LawyerAdapter.ViewHolder> {

    public interface OnLawyerClickListener {
        void onLawyerClick(Lawyer lawyer);
    }

    private final Context context;
    private List<Lawyer> lawyers;
    private List<Lawyer> allLawyers;
    private final OnLawyerClickListener listener;

    public LawyerAdapter(Context context, List<Lawyer> lawyers, OnLawyerClickListener listener) {
        this.context = context;
        this.lawyers = new ArrayList<>(lawyers);
        this.allLawyers = new ArrayList<>(lawyers);
        this.listener = listener;
    }

    public void updateData(List<Lawyer> newList) {
        this.lawyers = new ArrayList<>(newList);
        this.allLawyers = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        if (query == null || query.trim().isEmpty()) {
            lawyers = new ArrayList<>(allLawyers);
        } else {
            String q = query.toLowerCase().trim();
            lawyers = new ArrayList<>();
            for (Lawyer l : allLawyers) {
                if ((l.getName() != null && l.getName().toLowerCase().contains(q))
                        || (l.getSpecialty() != null && l.getSpecialty().toLowerCase().contains(q))
                        || (l.getLocation() != null && l.getLocation().toLowerCase().contains(q))) {
                    lawyers.add(l);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_lawyer_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Lawyer lawyer = lawyers.get(position);
        h.tvInitials.setText(lawyer.getAvatarInitials());
        h.tvName.setText(lawyer.getName());
        h.tvSpecialty.setText(lawyer.getSpecialty());
        h.tvRating.setText(String.valueOf(lawyer.getRating()));
        h.tvExperience.setText(lawyer.getExperienceYears() + " yrs exp");
        h.tvFee.setText(lawyer.getConsultationFee());
        h.tvLocation.setText(lawyer.getLocation());
        h.tvAvailability.setText(lawyer.isAvailable() ? "Available" : "Busy");
        h.tvAvailability.setAlpha(lawyer.isAvailable() ? 1f : 0.5f);
        h.card.setOnClickListener(v -> listener.onLawyerClick(lawyer));
    }

    @Override
    public int getItemCount() { return lawyers.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView tvInitials, tvName, tvSpecialty, tvRating, tvExperience, tvFee, tvLocation, tvAvailability;

        ViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card_lawyer);
            tvInitials = v.findViewById(R.id.tv_initials);
            tvName = v.findViewById(R.id.tv_lawyer_name);
            tvSpecialty = v.findViewById(R.id.tv_specialty);
            tvRating = v.findViewById(R.id.tv_rating);
            tvExperience = v.findViewById(R.id.tv_experience);
            tvFee = v.findViewById(R.id.tv_fee);
            tvLocation = v.findViewById(R.id.tv_location);
            tvAvailability = v.findViewById(R.id.tv_availability);
        }
    }
}
