package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void increaseUpdate(Object index, Resume resume);

    protected abstract void increaseSave(Object index, Resume resume);

    protected abstract void increaseDelete(Object index);

    protected abstract Resume increaseGet(Object index);

    protected abstract Object increaseGetIndex(String uuid);

    protected abstract boolean existIncrease(Object index);

    public void update(Resume resume) {
        Object index = getExistedSearchKey(resume.getUuid());
        increaseUpdate(index, resume);
    }

    public void save(Resume resume) {
        Object index = getNotExistedSearchKey(resume.getUuid());
        increaseSave(index, resume);
    }

    public void delete(String uuid) {
        Object index = getExistedSearchKey(uuid);
        increaseDelete(index);
    }

    public Resume get(String uuid) {
        Object index = getExistedSearchKey(uuid);
        return increaseGet(index);
    }

    private Object getExistedSearchKey(String uuid) {
        Object index = increaseGetIndex(uuid);
        if (!existIncrease(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object index = increaseGetIndex(uuid);
        if (existIncrease(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
