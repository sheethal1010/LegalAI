package com.legalai.app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.legalai.app.models.ChatMessage;
import com.legalai.app.models.LegalCase;
import com.legalai.app.network.GeminiClient;
import com.legalai.app.network.GeminiLegalService;
import com.legalai.app.network.MockAiService;
import com.legalai.app.nlp.LegalClassifier;
import com.legalai.app.utils.AppUtils;
import com.legalai.app.utils.FirebaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ChatMessage>> messages = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> typing = new MutableLiveData<>(false);
    private final MutableLiveData<String> nlpCategory = new MutableLiveData<>("");

    private final GeminiLegalService geminiService;
    private final LegalClassifier classifier;
    private final FirebaseHelper firebaseHelper;

    private String lastCategory = "";
    private String agentName = null;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        geminiService = new GeminiLegalService();
        classifier = new LegalClassifier(application);
        firebaseHelper = new FirebaseHelper();
        postWelcome();
    }

    private void postWelcome() {
        addBotMessage("👋 Welcome to Legal.AI! I'm your AI-powered Indian legal assistant.\n\nDescribe your legal issue and I'll provide guidance based on Indian law. What can I help you with today?");
    }

    public void setAgentContext(String name, String description) {
        this.agentName = name;
        geminiService.setAgentContext(name, description);
        List<ChatMessage> current = messages.getValue();
        if (current != null) current.clear();
        addBotMessage("👋 You're now chatting with *" + name + "*.\n\n" +
                (description != null ? description : "") +
                "\n\nHow can I assist you?");
    }

    public void sendMessage(String text) {
        // Add user message
        addMessage(new ChatMessage(text, ChatMessage.TYPE_USER));

        // NLP classification
        LegalClassifier.ClassificationResult result = classifier.classify(text);
        lastCategory = result.category;
        nlpCategory.postValue(result.category);

        // Show urgency action message
        if (result.urgent) {
            addMessage(new ChatMessage(
                    "🚨 Urgency detected. Prioritizing your case — a lawyer can be contacted immediately.",
                    ChatMessage.TYPE_ACTION));
        }

        // Show typing
        typing.postValue(true);

        // Call Gemini or fallback to mock
        if (GeminiClient.isApiKeySet() && AppUtils.isNetworkAvailable(getApplication())) {
            geminiService.sendMessage(text, new GeminiLegalService.GeminiCallback() {
                @Override
                public void onSuccess(String response) {
                    typing.postValue(false);
                    addBotMessage(response);
                    maybeSaveCase(text, result.category);
                }

                @Override
                public void onError(String error) {
                    typing.postValue(false);
                    String fallback = MockAiService.getResponse(lastCategory, text);
                    addBotMessage(fallback);
                }
            });
        } else {
            // Simulate delay for mock
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
                typing.postValue(false);
                String response = MockAiService.getResponse(lastCategory, text);
                addBotMessage(response);
                maybeSaveCase(text, result.category);
            }, 1500);
        }
    }

    private void maybeSaveCase(String query, String category) {
        if (!firebaseHelper.isLoggedIn()) return;
        LegalCase legalCase = new LegalCase(
                UUID.randomUUID().toString(),
                truncate(query, 50),
                category,
                query,
                LegalCase.STATUS_AI_PROCESSING,
                agentName != null ? agentName : "General AI",
                null,
                System.currentTimeMillis()
        );
        firebaseHelper.saveCase(legalCase, success -> {});
    }

    private String truncate(String s, int max) {
        if (s == null) return "";
        return s.length() > max ? s.substring(0, max) + "…" : s;
    }

    private void addBotMessage(String text) {
        addMessage(new ChatMessage(text, ChatMessage.TYPE_BOT));
    }

    private void addMessage(ChatMessage msg) {
        List<ChatMessage> current = messages.getValue();
        if (current == null) current = new ArrayList<>();
        List<ChatMessage> updated = new ArrayList<>(current);
        updated.add(msg);
        messages.postValue(updated);
    }

    public LiveData<List<ChatMessage>> getMessages() { return messages; }
    public LiveData<Boolean> isTyping() { return typing; }
    public LiveData<String> getNlpCategory() { return nlpCategory; }
}
