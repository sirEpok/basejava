package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new TreeMap<String, Resume>();

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
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[mapStorage.size()]);
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
    protected Integer searchIndex(String uuid) {
        int i = 0;
        for (Map.Entry e : mapStorage.entrySet()) {
            if (uuid == e.getKey()) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapStorage.containsKey(uuid);
    }
}
