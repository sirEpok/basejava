package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    public static final ListSection EMPTY = new ListSection("");

    private List<String> items;

    public ListSection() {
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        List<String> items_copy = new ArrayList<>(items);
        items_copy.removeIf(String::isEmpty);
        if (items_copy.size() < 1) {
            items_copy.add("");
        }
        this.items = items_copy;
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
        ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
