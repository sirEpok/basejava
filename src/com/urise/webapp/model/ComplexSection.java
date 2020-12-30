package com.urise.webapp.model;

import java.util.List;

public class ComplexSection extends Section {
    private final List<Experience> experiences;

    public ComplexSection(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexSection obj = (ComplexSection) o;

        return experiences.equals(obj.experiences);
    }

    @Override
    public int hashCode() {
        return experiences.hashCode();
    }

    @Override
    public String toString() {
        return experiences.toString();
    }
}
