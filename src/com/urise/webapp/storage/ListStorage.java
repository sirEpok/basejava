package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> resumeArrayList = new ArrayList<>();
    @Override
    public int size() {
        return resumeArrayList.size();
    }

    @Override
    public void clear() {
        resumeArrayList.clear();
    }

    @Override
    protected void increaseUpdate(Object index, Resume resume) {
        resumeArrayList.set((Integer) index, resume);
    }

    @Override
    public Resume[] getAll() {
        Resume[] arrayResume = resumeArrayList.toArray(new Resume[resumeArrayList.size()]);
        return arrayResume;
    }

    @Override
    protected void increaseSave(Object index, Resume resume) {
        resumeArrayList.add(resume);
    }

    @Override
    protected void increaseDelete(Object index) {
        resumeArrayList.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume increaseGet(Object index) {
        return resumeArrayList.get((Integer) index);
    }

    @Override
    protected Integer increaseGetIndex(String uuid) {
        for (int i = 0; i < resumeArrayList.size(); i++) {
            if (resumeArrayList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean existIncrease(Object index) {
        return index != null;
    }
}
