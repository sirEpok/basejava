package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapSearchKeyResumeStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected void executeUpdate(Object r, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    public List<Resume> executeCopySortedList() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    protected void executeSave(Object r, Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void executeDelete(Object resume) {
        mapStorage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume executeGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected Resume searchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }
}
