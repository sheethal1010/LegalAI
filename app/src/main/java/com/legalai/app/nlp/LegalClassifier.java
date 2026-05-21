package com.legalai.app.nlp;

import android.content.Context;
import android.util.Log;

public class LegalClassifier {
    private static final String TAG = "LegalClassifier";

    public static final String CAT_CRIMINAL = "Criminal Law";
    public static final String CAT_FAMILY = "Family Law";
    public static final String CAT_PROPERTY = "Property Law";
    public static final String CAT_CORPORATE = "Corporate Law";
    public static final String CAT_CONSUMER = "Consumer Protection";
    public static final String CAT_LABOUR = "Labour Law";
    public static final String CAT_IP = "Intellectual Property";
    public static final String CAT_CONSTITUTIONAL = "Constitutional Law";
    public static final String CAT_GENERAL = "General Legal";

    private static final String[][] KEYWORDS = {
        // Criminal
        {"fir", "arrest", "bail", "police", "crime", "theft", "murder", "assault", "ipc", "crpc", "accused", "custody", "jail", "prison", "chargesheet", "warrant"},
        // Family
        {"divorce", "marriage", "wife", "husband", "custody", "alimony", "maintenance", "dowry", "domestic violence", "child", "adoption", "matrimonial"},
        // Property
        {"property", "land", "house", "flat", "rent", "tenant", "lease", "sale deed", "registry", "mutation", "encumbrance", "real estate", "plot"},
        // Corporate
        {"company", "business", "contract", "partnership", "llp", "startup", "trademark", "gst", "incorporation", "mca", "shareholder", "director"},
        // Consumer
        {"consumer", "refund", "defective", "ecommerce", "online shopping", "product", "service", "complaint", "deficiency", "unfair trade"},
        // Labour
        {"job", "employee", "employer", "salary", "termination", "pf", "esic", "gratuity", "labour", "employment", "workplace", "posh", "harassment"},
        // IP
        {"trademark", "patent", "copyright", "design", "ip", "intellectual property", "brand", "infringement", "plagiarism"},
        // Constitutional
        {"fundamental rights", "constitution", "court", "high court", "supreme court", "pil", "writ", "habeas corpus", "election", "government"}
    };

    private static final String[] CATEGORIES = {
        CAT_CRIMINAL, CAT_FAMILY, CAT_PROPERTY, CAT_CORPORATE,
        CAT_CONSUMER, CAT_LABOUR, CAT_IP, CAT_CONSTITUTIONAL
    };

    private static final String[] URGENCY_KEYWORDS = {
        "arrested", "bail", "eviction", "court tomorrow", "fir", "police station",
        "emergency", "urgent", "immediately", "deadline", "hearing today", "warrant"
    };

    private final Context context;

    public LegalClassifier(Context context) {
        this.context = context;
    }

    public ClassificationResult classify(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ClassificationResult(CAT_GENERAL, false, 0.5f);
        }

        String lower = text.toLowerCase();
        int bestIndex = -1;
        int bestScore = 0;

        for (int i = 0; i < KEYWORDS.length; i++) {
            int score = 0;
            for (String kw : KEYWORDS[i]) {
                if (lower.contains(kw)) score++;
            }
            if (score > bestScore) {
                bestScore = score;
                bestIndex = i;
            }
        }

        String category = bestIndex >= 0 ? CATEGORIES[bestIndex] : CAT_GENERAL;
        float confidence = bestScore > 0 ? Math.min(0.5f + (bestScore * 0.1f), 0.99f) : 0.4f;
        boolean urgent = isUrgent(lower);

        Log.d(TAG, "Classified: " + category + " (score=" + bestScore + ", urgent=" + urgent + ")");
        return new ClassificationResult(category, urgent, confidence);
    }

    private boolean isUrgent(String lower) {
        for (String kw : URGENCY_KEYWORDS) {
            if (lower.contains(kw)) return true;
        }
        return false;
    }

    public static class ClassificationResult {
        public final String category;
        public final boolean urgent;
        public final float confidence;

        public ClassificationResult(String category, boolean urgent, float confidence) {
            this.category = category;
            this.urgent = urgent;
            this.confidence = confidence;
        }
    }
}
