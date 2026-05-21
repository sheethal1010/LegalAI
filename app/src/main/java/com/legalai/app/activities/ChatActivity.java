package com.legalai.app.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.legalai.app.R;
import com.legalai.app.adapters.ChatAdapter;
import com.legalai.app.viewmodels.ChatViewModel;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChat;
    private TextInputEditText etMessage;
    private FloatingActionButton fabSend;
    private LinearLayout typingIndicator;
    private TextView tvNlpResult;
    private TextView tvToolbarTitle;
    private ImageView ivBack;
    private ChatAdapter chatAdapter;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindViews();
        setupViewModel();
        setupRecyclerView();
        setupListeners();
        handleAgentIntent();
    }

    private void bindViews() {
        rvChat = findViewById(R.id.rv_chat);
        etMessage = findViewById(R.id.et_message);
        fabSend = findViewById(R.id.fab_send);
        typingIndicator = findViewById(R.id.typing_indicator);
        tvNlpResult = findViewById(R.id.tv_nlp_result);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        ivBack = findViewById(R.id.iv_back);
    }

    private void setupViewModel() {
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatViewModel.getMessages().observe(this, messages -> {
            chatAdapter.updateMessages(messages);
            if (!messages.isEmpty()) rvChat.scrollToPosition(messages.size() - 1);
        });
        chatViewModel.isTyping().observe(this, typing ->
                typingIndicator.setVisibility(typing ? View.VISIBLE : View.GONE));
        chatViewModel.getNlpCategory().observe(this, category -> {
            if (category != null && !category.isEmpty()) {
                tvNlpResult.setText("📂 " + category);
                tvNlpResult.setVisibility(View.VISIBLE);
            } else {
                tvNlpResult.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        chatAdapter = new ChatAdapter(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        rvChat.setLayoutManager(lm);
        rvChat.setAdapter(chatAdapter);
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> onBackPressed());
        fabSend.setOnClickListener(v -> sendMessage());
        etMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) { sendMessage(); return true; }
            return false;
        });
    }

    private void sendMessage() {
        String text = etMessage.getText() != null ? etMessage.getText().toString().trim() : "";
        if (TextUtils.isEmpty(text)) return;
        etMessage.setText("");
        chatViewModel.sendMessage(text);
    }

    private void handleAgentIntent() {
        String agentName = getIntent().getStringExtra("agent_name");
        String agentDescription = getIntent().getStringExtra("agent_description");
        if (agentName != null) {
            tvToolbarTitle.setText(agentName);
            chatViewModel.setAgentContext(agentName, agentDescription);
        }
    }
}
