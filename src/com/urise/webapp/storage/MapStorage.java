package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
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
    protected void executeUpdate(Object uuid, Resume resume) {
        mapStorage.replace(String.valueOf(uuid), resume);
    }

    @Override
    public List<Resume> executeCopySortedList() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    protected void executeSave(Object uuid, Resume resume) {
        mapStorage.put(String.valueOf(uuid), resume);
    }

    @Override
    protected void executeDelete(Object uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Resume executeGet(Object uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected String searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapStorage.containsKey(uuid);
    }
}
