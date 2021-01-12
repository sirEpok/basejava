package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void executeUpdate(Integer index, Resume resume) {
        storage[index] = resume;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public List<Resume> getResumeList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void executeSave(Integer index, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Переполнение хранилища", resume.getUuid());
        }
        insertElement(resume, index);
        size++;
    }

    @Override
    public void executeDelete(Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    public Resume executeGet(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract Integer searchKey(String uuid);
}
