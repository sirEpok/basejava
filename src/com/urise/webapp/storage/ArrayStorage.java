package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == r) {
                System.out.println("Такое резюме уже существует");
            } else if (storage.length == size) {
                System.out.println("База переполнена");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++){
            if (storage[i] == r) {
                storage[i] = r;
            } else {
                System.out.println("Такого резюме не существует");
            }
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++){
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            } else {
                System.out.println("Такого резюме не существует");
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else {
                System.out.println("Такого резюме не существует");
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
