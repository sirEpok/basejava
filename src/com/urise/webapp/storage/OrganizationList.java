package com.urise.webapp.storage;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Organization;

import java.util.List;
import java.util.Objects;

public class OrganizationList extends AbstractSection {
    private List<Organization> organizations;

    public OrganizationList() {
    }

    public OrganizationList(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizationList() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationList that = (OrganizationList) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
