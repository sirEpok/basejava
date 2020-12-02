package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ListStorage extends AbstractStorage {
    @Override
    public int size() {
        return resumeArrayList.size();
    }

    @Override
    public void clear() {
        resumeArrayList.clear();
    }

    @Override
    protected void listUpdate(Object index, Resume resume) {
        resumeArrayList.set((Integer) index, resume);
    }

    @Override
    public Resume[] getAll() {
        Resume[] arrayResume = new Resume[resumeArrayList.size()];
        for (int i = 0; i < resumeArrayList.size(); i++) {
            arrayResume[i] = resumeArrayList.get(i);
        }
        return arrayResume;
    }

    @Override
    protected void listSave(Object index, Resume resume) {
        resumeArrayList.add(resume);
    }

    @Override
    protected void listDelete(Object index) {
        resumeArrayList.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume listGet(Object index) {
        return resumeArrayList.get((Integer) index);
    }

    @Override
    protected Integer listGetIndex(String uuid) {
        for (int i = 0; i < resumeArrayList.size(); i++) {
            if (resumeArrayList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean existList(Object index) {
        if (index != null){
            return true;
        } else {
            return false;
        }
    }
}
