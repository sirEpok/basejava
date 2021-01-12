package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;

public class Organization extends AbstractSection {
    private final List<Experience> experiences;

    public Organization(Experience... experiences) {
        this(Arrays.asList(experiences));
    }

    public Organization(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization obj = (Organization) o;

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
