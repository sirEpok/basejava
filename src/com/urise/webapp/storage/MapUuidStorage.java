package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends ArrayStorage {
    private Map<String, Resume> mapUuidStorage = new TreeMap<>();

    @Override
    public int size() {
        return mapUuidStorage.size();
    }

    @Override
    public void clear() {
        mapUuidStorage.clear();
    }

    @Override
    protected void executeUpdate(Object uuid, Resume resume) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    public List<Resume> executeCopySorted() {
        return new ArrayList<>(mapUuidStorage.values());
    }

    @Override
    protected void executeSave(Object uuid, Resume resume) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    public void executeDelete(Object uuid) {
        mapUuidStorage.remove(uuid);
    }

    @Override
    public Resume executeGet(Object uuid) {
        return mapUuidStorage.get(uuid);
    }

    @Override
    protected Integer searchKey(String uuid) {
        return Integer.valueOf(uuid);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapUuidStorage.containsKey((String) uuid);
    }






}
