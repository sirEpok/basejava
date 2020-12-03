package com.urise.webapp.storage;

public abstract class AbstractArrayStorageTest extends  AbstractStorageTest {
    public AbstractArrayStorageTest(ArrayStorage arrayStorage) {
        super(new ArrayStorage());
    }

    public AbstractArrayStorageTest(SortedArrayStorage sortedArrayStorage) {
        super(new SortedArrayStorage());
    }
}
