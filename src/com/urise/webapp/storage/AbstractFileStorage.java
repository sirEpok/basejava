package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Каталог не должен быть пустым");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " не каталог");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " нет прав на изменение или чтение");
        }
        this.directory = directory;
    }

    @Override
    protected void executeUpdate(File file, Resume resume) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Ошибка записи файла", resume.getUuid(), e);
        }
    }

    @Override
    protected void executeSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Не удалось создать файл" + file.getAbsolutePath(), file.getName(), e);
        }
        executeUpdate(file, resume);
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected void executeDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Ошибка удаления файла", file.getName());
        }
    }

    @Override
    protected Resume executeGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения файла", file.getName(), e);
        }
    }

    @Override
    protected File searchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getResumeList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Ошибка чтения каталога", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(executeGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                executeDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Ошибка чтения каталога", null);
        }
        return list.length;
    }
}
