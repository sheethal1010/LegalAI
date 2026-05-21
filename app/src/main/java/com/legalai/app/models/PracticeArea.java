package com.legalai.app.models;

public class PracticeArea {
    private String id;
    private String title;
    private String emoji;
    private int lawyerCount;
    private int backgroundColor;

    public PracticeArea() {}

    public PracticeArea(String id, String title, String emoji, int lawyerCount, int backgroundColor) {
        this.id = id;
        this.title = title;
        this.emoji = emoji;
        this.lawyerCount = lawyerCount;
        this.backgroundColor = backgroundColor;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public int getLawyerCount() { return lawyerCount; }
    public void setLawyerCount(int lawyerCount) { this.lawyerCount = lawyerCount; }
    public int getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(int backgroundColor) { this.backgroundColor = backgroundColor; }
}
