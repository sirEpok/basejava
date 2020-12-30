package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final Link workLink;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Experience(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.workLink = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience obj = (Experience) o;

        if (!workLink.equals(obj.workLink)) return false;
        if (!startDate.equals(obj.startDate)) return false;
        if (!endDate.equals(obj.endDate)) return false;
        if (!title.equals(obj.title)) return false;
        return Objects.equals(description, obj.description);

    }

    @Override
    public int hashCode() {
        int result = workLink.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "workLink=" + workLink +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
