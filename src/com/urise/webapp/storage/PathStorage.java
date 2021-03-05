package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(dir, "каталог не должен быть null");

        this.streamSerializer = streamSerializer;
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " не каталог или не доступно для записи");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::executeDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path searchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void executeUpdate(Path path, Resume resume) {
        try {
            streamSerializer.executeWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Ошибка записи пути", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void executeSave(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Не удалось создать путь " + path, getFileName(path), e);
        }
        executeUpdate(path, resume);
    }

    @Override
    protected Resume executeGet(Path path) {
        try {
            return streamSerializer.executeRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения пути", getFileName(path), e);
        }
    }

    @Override
    protected void executeDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Ошибка удаления пути", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> getResumeList() {
        return getFilesList().map(this::executeGet).collect(Collectors.toList());
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", getFileName(directory));
        }
    }
}
