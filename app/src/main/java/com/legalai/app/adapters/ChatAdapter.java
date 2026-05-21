package com.legalai.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void updateMessages(List<ChatMessage> newMessages) {
        this.messages = new ArrayList<>(newMessages);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case ChatMessage.TYPE_USER:
                return new UserVH(inflater.inflate(R.layout.item_chat_user, parent, false));
            case ChatMessage.TYPE_ACTION:
                return new ActionVH(inflater.inflate(R.layout.item_chat_action, parent, false));
            default:
                return new BotVH(inflater.inflate(R.layout.item_chat_bot, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String msg = messages.get(position).getMessage();
        if (holder instanceof UserVH) ((UserVH) holder).tvMessage.setText(msg);
        else if (holder instanceof BotVH) ((BotVH) holder).tvMessage.setText(msg);
        else if (holder instanceof ActionVH) ((ActionVH) holder).tvMessage.setText(msg);
    }

    @Override
    public int getItemCount() { return messages.size(); }

    static class UserVH extends RecyclerView.ViewHolder {
        TextView tvMessage;
        UserVH(View v) { super(v); tvMessage = v.findViewById(R.id.tv_message); }
    }

    static class BotVH extends RecyclerView.ViewHolder {
        TextView tvMessage;
        BotVH(View v) { super(v); tvMessage = v.findViewById(R.id.tv_message); }
    }

    static class ActionVH extends RecyclerView.ViewHolder {
        TextView tvMessage;
        ActionVH(View v) { super(v); tvMessage = v.findViewById(R.id.tv_message); }
    }
}
