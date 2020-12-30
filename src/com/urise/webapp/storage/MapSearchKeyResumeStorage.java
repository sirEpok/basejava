package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapSearchKeyResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected void executeUpdate(Resume r, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    protected void executeSave(Resume r, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Resume resume) {
        mapStorage.remove((resume).getUuid());
    }

    @Override
    protected Resume executeGet(Resume resume) {
        return resume;
    }

    @Override
    protected Resume searchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }
}
