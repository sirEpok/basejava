package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

public class ResumeTestData {
    public Resume mockData(String uuid, String fullName) {
        Resume resumeTest = new Resume(uuid, fullName);

        resumeTest.addContact(ContactType.PHONE, "+7(954)35348");
        resumeTest.addContact(ContactType.SKYPE, "qwert");
        resumeTest.addContact(ContactType.E_MAIL, "test@test.com");
        resumeTest.addContact(ContactType.LINKED_IN,"test");
        resumeTest.addContact(ContactType.GIT_HUB, "test");
        resumeTest.addContact(ContactType.STACKOVERFLOW, "test");

        resumeTest.addSection(SectionType.PERSONAL, new TextSection("Тестовый"));
        resumeTest.addSection(SectionType.OBJECTIVE, new TextSection("test"));
        resumeTest.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("test1", "test2", "test3")));
        resumeTest.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("test1", "test2", "test3")));
        resumeTest.addSection(SectionType.EXPERIENCE, new Organization(
                Collections.singletonList(new Experience("Test1", "www:\\test1.ru",
                        new Experience.Position(1990, Month.JANUARY, "test1", "test1")))));
        return resumeTest;
    }
}
