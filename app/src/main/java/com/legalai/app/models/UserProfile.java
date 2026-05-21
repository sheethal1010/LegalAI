package com.legalai.app.models;

public class UserProfile {
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String location;
    private long createdAt;

    public UserProfile() {}

    public UserProfile(String uid, String name, String email, String phone, String location) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.createdAt = System.currentTimeMillis();
    }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public String getInitials() {
        if (name == null || name.isEmpty()) return "U";
        String[] parts = name.trim().split(" ");
        if (parts.length >= 2) return String.valueOf(parts[0].charAt(0)) + parts[1].charAt(0);
        return String.valueOf(parts[0].charAt(0));
    }
}
