package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapSearchKeyUuidStorage extends AbstractStorage {
    private Map<String, Resume> mapKeyStorage = new TreeMap<>();

    @Override
    public int size() {
        return mapKeyStorage.size();
    }

    @Override
    public void clear() {
        mapKeyStorage.clear();
    }

    @Override
    protected void executeUpdate(Object r, Resume resume) {
        mapKeyStorage.put(resume.getUuid(), resume);
    }

    @Override
    public List<Resume> executeCopySortedList() {
        return new ArrayList<>(mapKeyStorage.values());
    }

    @Override
    protected void executeSave(Object r, Resume resume) {
        mapKeyStorage.put(resume.getUuid(), resume);
    }

    @Override
    public void executeDelete(Object resume) {
        mapKeyStorage.remove(((Resume) resume).getUuid());
    }

    @Override
    public Resume executeGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected Resume searchKey(String uuid) {
        return mapKeyStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }
}
