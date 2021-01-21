package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStorage extends AbstractStorage<File> {
    private File directory;

    private StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "Каталог не должен быть пустым");
        this.streamSerializer = streamSerializer;
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
            streamSerializer.executeWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
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

    @Override
    protected void executeDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Ошибка удаления файла", file.getName());
        }
    }

    @Override
    protected Resume executeGet(File file) {
        try {
            return streamSerializer.executeRead(new BufferedInputStream(new FileInputStream(file)));
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
        return Stream.of(getFiles()).map(this::executeGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        Stream.of(getFiles()).forEach(this::executeDelete);
    }

    @Override
    public int size() {
        return (int) Stream.of(getFiles()).count();
    }

    public File[] getFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Ошибка чтения каталога", directory.getName());
        }
        return files;
    }
}
