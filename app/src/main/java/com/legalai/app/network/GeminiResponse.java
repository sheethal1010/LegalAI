package com.legalai.app.network;

import java.util.List;

public class GeminiResponse {
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() { return candidates; }

    public String getFirstText() {
        if (candidates != null && !candidates.isEmpty()) {
            Candidate c = candidates.get(0);
            if (c.getContent() != null && c.getContent().getParts() != null
                    && !c.getContent().getParts().isEmpty()) {
                return c.getContent().getParts().get(0).getText();
            }
        }
        return null;
    }

    public static class Candidate {
        private Content content;
        public Content getContent() { return content; }
    }

    public static class Content {
        private List<Part> parts;
        public List<Part> getParts() { return parts; }
    }

    public static class Part {
        private String text;
        public String getText() { return text; }
    }
}
