package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapSearchKeyUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> mapKeyStorage = new HashMap<>();

    @Override
    public int size() {
        return mapKeyStorage.size();
    }

    @Override
    public void clear() {
        mapKeyStorage.clear();
    }

    @Override
    protected void executeUpdate(String uuid, Resume resume) {
        mapKeyStorage.replace(uuid, resume);
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(mapKeyStorage.values());
    }

    @Override
    protected void executeSave(String uuid, Resume resume) {
        mapKeyStorage.put(uuid, resume);
    }

    @Override
    public void executeDelete(String uuid) {
        mapKeyStorage.remove(uuid);
    }

    @Override
    public Resume executeGet(String uuid) {
        return mapKeyStorage.get(uuid);
    }

    @Override
    protected String searchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return mapKeyStorage.containsKey(uuid);
    }
}
