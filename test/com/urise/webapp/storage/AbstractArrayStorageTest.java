package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

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
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertTrue("uuid1" ,resume == storage.get(UUID_1));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(RESUME_1.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume resume = new Resume(UUID_1);
        assertNotNull("UUID_1", resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() throws Exception {
        for (int i = 0; i <= AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
            if (AbstractArrayStorage.STORAGE_LIMIT + 1 <= 10_000) {
                storage.save(new Resume());
            } else {
                Assert.fail();
            }
        }
        storage.save(new Resume());
    }

}