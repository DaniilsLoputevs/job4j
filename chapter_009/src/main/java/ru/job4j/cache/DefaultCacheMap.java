package ru.job4j.cache;

import daniils.IOHelper;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultCacheMap implements CacheMap {
    private final HashMap<String, SoftReference<List<String>>> cacheMap = new HashMap<>();


    @Override
    public void load(File file) {
        this.loadCachesByName(file.getName());
    }

    @Override
    public void load(String fileName) {
        this.loadCachesByName(fileName);
    }

    @Override
    public void load(String fileName, List<String> fileContent) {
        putInMap(fileName, fileContent);
    }

    @Override
    public List<String> getCacheContent(String fileName) {
        List<String> rsl = new ArrayList<>();
        if (fileName != null) {
            if (cacheMap.containsKey(fileName)) {
                rsl = getOrLoadCache(fileName);
            } else {
                rsl = loadCachesByName(fileName);
            }
        }
        return rsl;
    }

    private List<String> loadCachesByName(String fileName) {
        List<String> fileContent = new ArrayList<>();
        try {
            var filePath = DefaultCacheMap.class.getClassLoader().getResource("caches/" + fileName).getFile();
            fileContent = IOHelper.readFileToList(filePath);
            putInMap(fileName, fileContent);
        } catch (Exception e) {
            System.out.println("WRONG: Cache's directory doesn't contains file with this name: " + fileName);
        }
        return fileContent;
    }

    private List<String> getOrLoadCache(String fileName) {
        var rsl = this.cacheMap.get(fileName).get();
        return (rsl == null) ? this.loadCachesByName(fileName) : rsl;
    }

    private void putInMap(String fileName, List<String> fileContent) {
        this.cacheMap.put(fileName, new SoftReference<>(fileContent));
    }

}