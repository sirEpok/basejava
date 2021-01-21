package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<Experience> places;

    public Organization() {
    }

    public Organization(Experience... places) {
        this(Arrays.asList(places));
    }

    public Organization(List<Experience> places) {
        this.places = places;
    }

    public List<Experience> getPlaces() {
        return places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(places);
    }

    @Override
    public String toString() {
        return places.toString();
    }
}
