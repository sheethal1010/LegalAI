package com.legalai.app.models;

public class GlossaryTerm {
    private String term;
    private String definition;
    private String category;
    private boolean expanded;

    public GlossaryTerm() {}

    public GlossaryTerm(String term, String definition, String category) {
        this.term = term;
        this.definition = definition;
        this.category = category;
        this.expanded = false;
    }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }
}
