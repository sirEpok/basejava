package com.urise.webapp.model;

public enum Contacts {
    PHONE("Телефон"),
    SKYPE("Skype"),
    E_MAIL("Электронная почта"),
    LINKED_IN("LinkedIn"),
    GIT_HUB("GitHub"),
    STACKOVERFLOW("StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
