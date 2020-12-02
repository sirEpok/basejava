package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public abstract class AbstractStorage implements Storage {
    ArrayList<Resume> resumeArrayList = new ArrayList<>();

    protected abstract void listUpdate(Object index, Resume resume);

    protected abstract void listSave(Object index, Resume resume);

    protected abstract void listDelete(Object index);

    protected abstract Resume listGet(Object index);

    protected abstract Object listGetIndex(String uuid);

    protected abstract boolean existList(Object index);

    public void update(Resume resume) {
        Object index = getExistedSearchKey(resume.getUuid());
        listUpdate(index, resume);
    }

    public void save(Resume resume) {
        Object index = getNotExistedSearchKey(resume.getUuid());
        listSave(index, resume);
    }

    public void delete(String uuid) {
        Object index = getExistedSearchKey(uuid);
        listDelete(index);
    }

    public Resume get(String uuid) {
        Object index = getExistedSearchKey(uuid);
        return listGet(index);
    }

    private Object getExistedSearchKey(String uuid) {
        Object index = listGetIndex(uuid);
        if (!existList(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object index = listGetIndex(uuid);
        if (existList(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
