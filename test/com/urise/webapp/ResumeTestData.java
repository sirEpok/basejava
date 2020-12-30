package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resumeTest = new Resume("uuid1", "Anton");

        resumeTest.addContact(Contacts.PHONE, "+7(954)35348");
        resumeTest.addContact(Contacts.SKYPE, "qwert");
        resumeTest.addContact(Contacts.E_MAIL, "test@test.com");
        resumeTest.addContact(Contacts.LINKED_IN,"test");
        resumeTest.addContact(Contacts.GIT_HUB, "test");
        resumeTest.addContact(Contacts.STACKOVERFLOW, "test");

        LocalDate startDate = LocalDate.of(1914, 07, 28);
        LocalDate endDate = LocalDate.of(1915, 07, 28);

        resumeTest.addSection(Sections.PERSONAL, new TextSection("Тестовый"));
        resumeTest.addSection(Sections.OBJECTIVE, new TextSection("test"));
        resumeTest.addSection(Sections.ACHIEVEMENT, new ListSection(Arrays.asList("test1", "test2", "test3")));
        resumeTest.addSection(Sections.QUALIFICATIONS, new ListSection(Arrays.asList("test1", "test2", "test3")));
        resumeTest.addSection(Sections.EXPERIENCE, new ComplexSection(
                Collections.singletonList(new Experience("Test1", "www:\\test1.ru", startDate, endDate, "Test1", "test1"))));

        System.out.println(resumeTest);
        System.out.println(resumeTest.contacts);
        System.out.println(resumeTest.sections);
    }
}
