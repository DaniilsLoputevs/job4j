package ru.job4j.cache;

import java.io.File;
import java.util.List;

public interface CacheMap {
    void load(File txtFile);

    void load(String txtFilePath);

    List<String> getCacheContent(String fileName);

    boolean cachesContains(String fileName);
}
