package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapSearchKeyUuidStorageTest.class,
        MapSearchKeyResumeStorageTest.class,
        SortedArrayStorageTest.class,
        ObjectPathStorageTest.class,
        ObjectFileStorageTest.class
})
public class SuiteClassTest {
}
