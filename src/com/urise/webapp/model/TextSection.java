package com.urise.webapp.model;

public class TextSection extends AbstractSection {
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection obj = (TextSection) o;

        return content.equals(obj.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
