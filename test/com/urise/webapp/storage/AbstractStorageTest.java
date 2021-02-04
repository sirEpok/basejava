package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = ResumeTestData.mockData(UUID_1, "Name1");
        RESUME_2 = ResumeTestData.mockData(UUID_2, "Name2");
        RESUME_3 = ResumeTestData.mockData(UUID_3, "Name3");
        RESUME_4 = ResumeTestData.mockData(UUID_4, "Name4");

        RESUME_1.setContact(ContactType.PHONE, "phone number");
        RESUME_1.setContact(ContactType.SKYPE, "skype");
        RESUME_1.setContact(ContactType.E_MAIL, "email");
        RESUME_1.setContact(ContactType.LINKED_IN, "linkedin");
        RESUME_1.setContact(ContactType.GIT_HUB, "github");
        RESUME_1.setContact(ContactType.STACKOVERFLOW, "stack-overflow");
        RESUME_1.setContact(ContactType.HOME_PAGE, "home page1");

        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection("Младший специалист"));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection("Самостоятельный"));
        RESUME_1.setSection(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<String>() {{
            add("Разобрался самостоятельно в Bootstrap");
        }}));
        RESUME_1.setSection(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<String>() {{
            add("Java SE");
        }}));

        RESUME_1.setSection(SectionType.EXPERIENCE, new Organization(
                new Experience("Test", "http://test.ru/",
                        new Experience.Position(1985, Month.APRIL, 1990, Month.FEBRUARY, "Test", "Test")),
                new Experience("Test", "http://test.ru/",
                        new Experience.Position(1990, Month.AUGUST, "Test", "Test"))));

        RESUME_1.setSection(SectionType.EDUCATION, new Organization(
                new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                        new Experience.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер", "программист Fortran, C"),
                        new Experience.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура", "программист С, С++"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_2, "Name1");
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resume = new Resume("dummy");
        storage.update(resume);
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> list = storage.getAllSorted();
        List<Resume> sortedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(sortedResumes);
        assertEquals(list, sortedResumes);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

//    @Test
//    public void mockTestData() throws Exception {
//        ResumeTestData rtd = new ResumeTestData();
//        System.out.println(rtd.mockData("123", "Anton").contacts);
//        System.out.println(rtd.mockData("123", "Anton").sections);
//    }
}
