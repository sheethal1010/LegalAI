package com.legalai.app.models;

public class AiAgent {
    private String id;
    private String title;
    private String description;
    private String emoji;
    private String category;
    private double rating;
    private String usageCount;
    private String systemPrompt;

    public AiAgent() {}

    public AiAgent(String id, String title, String description, String emoji,
                   String category, double rating, String usageCount, String systemPrompt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emoji = emoji;
        this.category = category;
        this.rating = rating;
        this.usageCount = usageCount;
        this.systemPrompt = systemPrompt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getUsageCount() { return usageCount; }
    public void setUsageCount(String usageCount) { this.usageCount = usageCount; }
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }
}
