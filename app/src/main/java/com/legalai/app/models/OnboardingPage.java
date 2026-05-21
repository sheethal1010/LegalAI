package com.legalai.app.models;

public class OnboardingPage {
    private String emoji;
    private String title;
    private String subtitle;

    public OnboardingPage() {}

    public OnboardingPage(String emoji, String title, String subtitle) {
        this.emoji = emoji;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
}
