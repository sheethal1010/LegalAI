package com.legalai.app.network;

import java.util.List;

public class GeminiRequest {
    private List<Content> contents;
    private GenerationConfig generationConfig;

    public GeminiRequest(List<Content> contents) {
        this.contents = contents;
        this.generationConfig = new GenerationConfig(1024, 0.7f);
    }

    public List<Content> getContents() { return contents; }
    public GenerationConfig getGenerationConfig() { return generationConfig; }

    public static class Content {
        private String role;
        private List<Part> parts;

        public Content(String role, String text) {
            this.role = role;
            this.parts = java.util.Arrays.asList(new Part(text));
        }

        public String getRole() { return role; }
        public List<Part> getParts() { return parts; }
    }

    public static class Part {
        private String text;
        public Part(String text) { this.text = text; }
        public String getText() { return text; }
    }

    public static class GenerationConfig {
        private int maxOutputTokens;
        private float temperature;

        public GenerationConfig(int maxOutputTokens, float temperature) {
            this.maxOutputTokens = maxOutputTokens;
            this.temperature = temperature;
        }

        public int getMaxOutputTokens() { return maxOutputTokens; }
        public float getTemperature() { return temperature; }
    }
}
