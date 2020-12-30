package com.urise.webapp.model;

import java.util.List;

public class ListSection extends Section {
    private final List<String> items;

    public ListSection(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection obj = (ListSection) o;

        return items.equals(obj.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
