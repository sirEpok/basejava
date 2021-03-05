package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void executeUpdate(SK searchKey, Resume resume);

    protected abstract void executeSave(SK searchKey, Resume resume);

    protected abstract void executeDelete(SK searchKey);

    protected abstract Resume executeGet(SK searchKey);

    protected abstract SK searchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getResumeList();

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        executeUpdate(searchKey, resume);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        executeSave(searchKey, resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        executeDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return executeGet(searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " не существует");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " уже существует");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getResumeList();
        list.sort(Resume::compareTo);
        return list;
    }
}
