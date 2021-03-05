package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    private static final long serialVersionUID = 1L;

    public NotExistStorageException(String uuid) {
        super("Резюме не существует: " + uuid, uuid);
    }
}