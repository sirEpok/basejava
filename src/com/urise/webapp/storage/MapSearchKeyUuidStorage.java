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
    protected void executeUpdate(Object uuid, Resume resume) {
        mapKeyStorage.replace(String.valueOf(uuid), resume);
    }

    @Override
    public List<Resume> executeCopySortedList() {
        return new ArrayList<>(mapKeyStorage.values());
    }

    @Override
    protected void executeSave(Object uuid, Resume resume) {
        mapKeyStorage.put(String.valueOf(uuid), resume);
    }

    @Override
    public void executeDelete(Object uuid) {
        mapKeyStorage.remove(uuid);
    }

    @Override
    public Resume executeGet(Object uuid) {
        return mapKeyStorage.get(uuid);
    }

    @Override
    protected String searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapKeyStorage.containsKey(uuid);
    }
}
