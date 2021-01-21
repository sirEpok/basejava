package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.*;

public class ResumeTestData {
    public static Resume mockData(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContact(ContactType.PHONE, "phone number");
        resume.setContact(ContactType.SKYPE, "skype");
        resume.setContact(ContactType.E_MAIL, "email");
        resume.setContact(ContactType.LINKED_IN, "linkedin");
        resume.setContact(ContactType.GIT_HUB, "github");
        resume.setContact(ContactType.STACKOVERFLOW, "stack-overflow");
        resume.setContact(ContactType.HOME_PAGE, "home page1");

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Младший специалист"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Самостоятельный"));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<String>() {{
            add("Разобрался самостоятельно в Bootstrap");
        }}));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<String>() {{
            add("Java SE");
        }}));

        resume.setSection(SectionType.EXPERIENCE, new Organization(
                new Experience("Test", "http://test.ru/",
                        new Experience.Position(1985, Month.APRIL, 1990, Month.FEBRUARY, "Test", "Test")),
                new Experience("Test", "http://test.ru/",
                        new Experience.Position(1990, Month.AUGUST, "Test", "Test"))));

        resume.setSection(SectionType.EDUCATION, new Organization(
                new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                        new Experience.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер", "программист Fortran, C"),
                        new Experience.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура", "программист С, С++"))));

        return resume;
    }

    public static void main(String[] args) {
        Resume resume = mockData("001","Григорий");

        for (ContactType contactType : ContactType.values()) {
            System.out.println(contactType.getTitle() + " : " + resume.getContacts().get(contactType));
        }
        System.out.println();
        for (SectionType sectionType : SectionType.values()) {
            System.out.println(sectionType.getTitle());
            System.out.println(resume.getSections().get(sectionType));
        }
    }
}
