package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<Experience.Position> places;

    public Organization() {
    }

    public Organization(List<Organization> institutions) {
    }

    public Organization(Link homePage, List<Experience.Position> places) {
        this.homePage = homePage;
        this.places = places;
    }

    public Link getHomePage() {
        return homePage;
    }


    public List<Experience.Position> getPlaces() {
        return places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, places);
    }

    @Override
    public String toString() {
        return "Организация(" + homePage + ", " + places + ')';
    }
}
