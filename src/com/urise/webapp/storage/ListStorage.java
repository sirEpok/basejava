package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void executeUpdate(Integer index, Resume resume) {
        listStorage.set(index, resume);
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(listStorage);
    }

    @Override
    protected void executeSave(Integer index, Resume resume) {
        listStorage.add(resume);
    }

    @Override
    protected void executeDelete(Integer index) {
        listStorage.remove((index).intValue());
    }

    @Override
    protected Resume executeGet(Integer index) {
        return listStorage.get(index);
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
    protected boolean isExist(Integer index) {
        return index != null;
    }
}
