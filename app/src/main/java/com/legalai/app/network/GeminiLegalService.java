package com.legalai.app.network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeminiLegalService {

    private static final String SYSTEM_PROMPT =
            "You are Legal.AI, an expert Indian legal assistant powered by Gemini. " +
            "You have deep knowledge of Indian law including IPC, CrPC, Constitution of India, " +
            "Contract Act, Consumer Protection Act, Property Law, Family Law, Labour Law, and more. " +
            "Provide accurate, helpful, and empathetic legal guidance. " +
            "Always clarify that your advice is for informational purposes only and recommend " +
            "consulting a qualified advocate for formal legal proceedings. " +
            "Be concise but thorough. Use simple language that non-lawyers can understand. " +
            "When relevant, mention applicable sections of Indian law. " +
            "Format responses clearly with numbered steps when giving procedures.";

    private final List<GeminiRequest.Content> conversationHistory = new ArrayList<>();

    public GeminiLegalService() {
        // Seed with system context as first user+model exchange
        conversationHistory.add(new GeminiRequest.Content("user", SYSTEM_PROMPT));
        conversationHistory.add(new GeminiRequest.Content("model",
                "I'm Legal.AI, your AI-powered Indian legal assistant. I'm here to help you understand your legal rights and options. How can I assist you today?"));
    }

    public void setAgentContext(String agentName, String agentDescription) {
        conversationHistory.clear();
        String agentPrompt = SYSTEM_PROMPT + "\n\nYou are currently acting as: " + agentName +
                "\nSpecialization: " + (agentDescription != null ? agentDescription : "") +
                "\nFocus your responses specifically on this domain.";
        conversationHistory.add(new GeminiRequest.Content("user", agentPrompt));
        conversationHistory.add(new GeminiRequest.Content("model",
                "I'm now acting as " + agentName + ". How can I help you today?"));
    }

    public void sendMessage(String userMessage, GeminiCallback callback) {
        conversationHistory.add(new GeminiRequest.Content("user", userMessage));

        GeminiRequest request = new GeminiRequest(new ArrayList<>(conversationHistory));

        GeminiClient.getInstance().generateContent(GeminiClient.API_KEY, request)
                .enqueue(new Callback<GeminiResponse>() {
                    @Override
                    public void onResponse(Call<GeminiResponse> call, Response<GeminiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String text = response.body().getFirstText();
                            if (text != null && !text.isEmpty()) {
                                conversationHistory.add(new GeminiRequest.Content("model", text));
                                callback.onSuccess(text);
                            } else {
                                callback.onError("Empty response from AI");
                            }
                        } else {
                            callback.onError("API error: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<GeminiResponse> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }

    public interface GeminiCallback {
        void onSuccess(String response);
        void onError(String error);
    }
}
