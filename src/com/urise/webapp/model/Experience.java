package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link workLink;
    private List<Position> positions = new ArrayList<>();

    public Experience(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Experience(Link workLink, List<Position> positions) {
        this.workLink = workLink;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience obj = (Experience) o;

        return Objects.equals(workLink, obj.workLink) && Objects.equals(positions, obj.positions);

    }

    @Override
    public int hashCode() {
        return Objects.hash(workLink, positions);
    }

    @Override
    public String toString() {
        return "Организация (" + workLink + ", " + positions + ")";
    }

    public static class Position implements Serializable{
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Позиция (с " + startDate + " по " + endDate + ", " + title + ", " + description + ")";
        }
    }
}
