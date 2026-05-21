package com.legalai.app.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.models.GlossaryTerm;

import java.util.ArrayList;
import java.util.List;

public class GlossaryAdapter extends RecyclerView.Adapter<GlossaryAdapter.ViewHolder> {

    private final Context context;
    private List<GlossaryTerm> terms;
    private final List<GlossaryTerm> allTerms;

    public GlossaryAdapter(Context context, List<GlossaryTerm> terms) {
        this.context = context;
        this.terms = new ArrayList<>(terms);
        this.allTerms = new ArrayList<>(terms);
    }

    public void filter(String query) {
        if (query == null || query.trim().isEmpty()) {
            terms = new ArrayList<>(allTerms);
        } else {
            String q = query.toLowerCase().trim();
            terms = new ArrayList<>();
            for (GlossaryTerm t : allTerms) {
                if (t.getTerm().toLowerCase().contains(q)
                        || t.getDefinition().toLowerCase().contains(q)
                        || t.getCategory().toLowerCase().contains(q)) {
                    terms.add(t);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_glossary_term, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        GlossaryTerm term = terms.get(position);
        h.tvTerm.setText(term.getTerm());
        h.tvCategory.setText(term.getCategory());
        h.tvDefinition.setText(term.getDefinition());
        h.tvDefinition.setVisibility(term.isExpanded() ? View.VISIBLE : View.GONE);
        h.ivChevron.setRotation(term.isExpanded() ? 90f : 0f);

        h.itemView.setOnClickListener(v -> {
            term.setExpanded(!term.isExpanded());
            ObjectAnimator.ofFloat(h.ivChevron, "rotation", term.isExpanded() ? 0f : 90f, term.isExpanded() ? 90f : 0f).start();
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() { return terms.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTerm, tvCategory, tvDefinition;
        ImageView ivChevron;

        ViewHolder(View v) {
            super(v);
            tvTerm = v.findViewById(R.id.tv_term);
            tvCategory = v.findViewById(R.id.tv_term_category);
            tvDefinition = v.findViewById(R.id.tv_definition);
            ivChevron = v.findViewById(R.id.iv_chevron);
        }
    }
}
