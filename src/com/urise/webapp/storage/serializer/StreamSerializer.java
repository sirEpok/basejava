package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void executeWrite(Resume resume, OutputStream os) throws IOException;

    Resume executeRead(InputStream is) throws IOException;
}
