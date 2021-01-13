package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.OrganizationList;

import java.time.Month;
import java.util.*;

public class ResumeTestData {
    public static Resume mockData(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        Map<ContactType, String> contact = new LinkedHashMap<>();
        contact.put(ContactType.PHONE, "1(234)567-8910");
        contact.put(ContactType.SKYPE, "test");
        contact.put(ContactType.E_MAIL, "test@test.ru");
        contact.put(ContactType.LINKED_IN, "Профиль LinkedIn");
        contact.put(ContactType.GIT_HUB, "Профиль GitHub");
        contact.put(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        contact.put(ContactType.HOME_PAGE, "Домашняя страница");

        resume.setContacts(contact);

        Map<SectionType, AbstractSection> positions = new LinkedHashMap<>();
        positions.put(SectionType.OBJECTIVE, new TextSection("Младший специалист"));
        positions.put(SectionType.PERSONAL, new TextSection("Самостоятельный"));
        positions.put(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<String>() {{
            add("Разобрался самостоятельно в Bootstrap");
        }}));
        positions.put(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<String>() {{
            add("Java SE");
        }}));
        List<Organization> work = new ArrayList<>();
        work.add(new Organization(new Link("Test", "http://test.ru/"),
                new ArrayList<Experience.Position>() {{
                    add((new Experience.Position(1985, Month.APRIL, 1990, Month.FEBRUARY, "Test", "test")));
                }}));
        work.add(new Organization(new Link("Test", "http://test.ru/"),
                new ArrayList<Experience.Position>() {{
                    add(new Experience.Position(1990, Month.AUGUST,"Это лучшая работа в мире", "Бог")) ;
                }}));
        OrganizationList listOrg = new OrganizationList(work);
        positions.put(SectionType.EXPERIENCE, listOrg);
        work = new ArrayList<>();
        work.add(new Organization(new Link("Test", "http://test.ru/"),
                new ArrayList<Experience.Position>() {{
                    add(new Experience.Position(1980, Month.SEPTEMBER, 1983, Month.AUGUST, "Test", "Test"));
                }}));
        work.add(new Organization(new Link("Test", "http://test.ru/"),
                new ArrayList<Experience.Position>() {{
                    add(new Experience.Position(1983, Month.SEPTEMBER, 1985, Month.DECEMBER, "Test", "Test"));
                }}));
        positions.put(SectionType.EDUCATION, new OrganizationList(work));
        resume.setSections(positions);
        return resume;
    }

    public static void main(String[] args) {
        Resume resume = mockData("1","Григорий");

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
