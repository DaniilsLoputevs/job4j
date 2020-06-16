package ru.job4j.cache;

import daniils.IOHelper;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;

public class DefaultCacheMap implements CacheMap {
    private final HashMap<String, SoftReference<List<String>>> cacheMap = new HashMap<>();


    @Override
    public void load(File txtFile) {
        this.loadCachesByName(txtFile.getName());
    }

    @Override
    public void load(String txtFileName) {
        this.loadCachesByName(txtFileName);
    }

    @Override
    public List<String> getCacheContent(String fileName) {
        var temp = this.cacheMap.get(fileName);
        if (fileName != null && !this.cachesContains(fileName) && temp == null) {
            this.loadCachesByName(fileName);
        }
        return this.cacheMap.get(fileName).get();
    }

    @Override
    public boolean cachesContains(String fileName) {
        return this.cacheMap.containsKey(fileName);
    }

    public void loadCachesByName(String fileName) {
        try {
            String filePath;
            filePath = DefaultCacheMap.class.getClassLoader().getResource("caches/" + fileName).getFile();
//            System.out.println("filePath: " + filePath);

            var temp = IOHelper.readFileToList(filePath);
            var fileContentRef = new SoftReference<>(temp);
            this.cacheMap.put(fileName, fileContentRef);
        } catch (Exception e) {
            System.out.println("WRONG: Cache's directory doesn't contains file with this name: " + fileName);
        }
    }

}