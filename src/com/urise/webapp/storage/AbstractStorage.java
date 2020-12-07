package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void executeUpdate(Object index, Resume resume);

    protected abstract void executeSave(Object index, Resume resume);

    protected abstract void executeDelete(Object index);

    protected abstract Resume executeGet(Object index);

    protected abstract Object executeSearchIndex(String uuid);

    protected abstract boolean executeSearchExistResume(Object index);

    public void update(Resume resume) {
        Object index = getExistedSearchKey(resume.getUuid());
        executeUpdate(index, resume);
    }

    public void save(Resume resume) {
        Object index = getNotExistedSearchKey(resume.getUuid());
        executeSave(index, resume);
    }

    public void delete(String uuid) {
        Object index = getExistedSearchKey(uuid);
        executeDelete(index);
    }

    public Resume get(String uuid) {
        Object index = getExistedSearchKey(uuid);
        return executeGet(index);
    }

    private Object getExistedSearchKey(String uuid) {
        Object index = executeSearchIndex(uuid);
        if (!executeSearchExistResume(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object index = executeSearchIndex(uuid);
        if (executeSearchExistResume(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
