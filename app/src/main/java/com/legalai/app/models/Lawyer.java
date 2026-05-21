package com.legalai.app.models;

public class Lawyer {
    private String id;
    private String name;
    private String specialty;
    private double rating;
    private int reviewCount;
    private int experienceYears;
    private String consultationFee;
    private String bio;
    private String location;
    private boolean available;
    private String avatarInitials;
    private String phone;

    public Lawyer() {}

    public Lawyer(String id, String name, String specialty, double rating,
                  int reviewCount, int experienceYears, String consultationFee,
                  String bio, String location, boolean available, String avatarInitials, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.experienceYears = experienceYears;
        this.consultationFee = consultationFee;
        this.bio = bio;
        this.location = location;
        this.available = available;
        this.avatarInitials = avatarInitials;
        this.phone = phone;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    public String getConsultationFee() { return consultationFee; }
    public void setConsultationFee(String consultationFee) { this.consultationFee = consultationFee; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public String getAvatarInitials() { return avatarInitials; }
    public void setAvatarInitials(String avatarInitials) { this.avatarInitials = avatarInitials; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
