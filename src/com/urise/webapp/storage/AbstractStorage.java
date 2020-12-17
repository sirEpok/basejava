package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract void executeUpdate(Object searchKey, Resume resume);

    protected abstract void executeSave(Object searchKey, Resume resume);

    protected abstract void executeDelete(Object searchKey);

    protected abstract Resume executeGet(Object searchKey);

    protected abstract Object searchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> executeCopySortedList();

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        executeUpdate(searchKey, resume);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        executeSave(searchKey, resume);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        executeDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return executeGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = executeCopySortedList();
        Collections.sort(list);
        return list;
    }
}
