package com.legalai.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.models.NotificationItem;
import com.legalai.app.utils.AppUtils;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context context;
    private final List<NotificationItem> items;

    public NotificationAdapter(Context context, List<NotificationItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        NotificationItem item = items.get(position);
        h.tvEmoji.setText(item.getEmoji());
        h.tvTitle.setText(item.getTitle());
        h.tvMessage.setText(item.getMessage());
        h.tvTime.setText(AppUtils.formatTimestamp(item.getTimestamp()));
        h.viewUnread.setVisibility(item.isRead() ? View.GONE : View.VISIBLE);
        h.itemView.setAlpha(item.isRead() ? 0.75f : 1f);
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmoji, tvTitle, tvMessage, tvTime;
        View viewUnread;

        ViewHolder(View v) {
            super(v);
            tvEmoji = v.findViewById(R.id.tv_notif_emoji);
            tvTitle = v.findViewById(R.id.tv_notif_title);
            tvMessage = v.findViewById(R.id.tv_notif_message);
            tvTime = v.findViewById(R.id.tv_notif_time);
            viewUnread = v.findViewById(R.id.view_unread_dot);
        }
    }
}
