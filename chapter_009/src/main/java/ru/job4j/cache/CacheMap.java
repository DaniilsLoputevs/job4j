package ru.job4j.cache;

import java.io.File;
import java.util.List;

/**
 * All cache files must:
 * - located in Cache Directory(src/main/resources/caches/cache_file.txt)
 * - have extension *.txt
 */
public interface CacheMap {
    void load(File file);

    void load(String fileName);

    void load(String fileName, List<String> fileContent);

    List<String> getCacheContent(String fileName);

}
