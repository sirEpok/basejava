package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();
    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    protected void executeUpdate(Object index, Resume resume) {
        listStorage.set((Integer) index, resume);
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[listStorage.size()]);
    }

    @Override
    protected void executeSave(Object index, Resume resume) {
        listStorage.add(resume);
    }

    @Override
    protected void executeDelete(Object index) {
        listStorage.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume executeGet(Object index) {
        return listStorage.get((Integer) index);
    }

    @Override
    protected Integer searchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}
