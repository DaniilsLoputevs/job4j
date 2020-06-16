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
        List<String> rsl = null;
        if (fileName != null) {
            if (isCacheValueClean(fileName)) {
                this.loadCachesByName(fileName);
            }
            var temp = this.cacheMap.get(fileName);
            if (temp != null) {
                rsl = temp.get();
            }
        }
        return rsl;
    }

    private void loadCachesByName(String fileName) {
        try {
            var filePath = DefaultCacheMap.class.getClassLoader().getResource("caches/" + fileName).getFile();
//            System.out.println("filePath: " + filePath);

            var temp = IOHelper.readFileToList(filePath);
            this.cacheMap.put(fileName, new SoftReference<>(temp));
        } catch (Exception e) {
            System.out.println("WRONG: Cache's directory doesn't contains file with this name: " + fileName);
        }
    }

    private boolean isCacheValueClean(String fileName) {
        var rsl = true;
        var temp = this.cacheMap.get(fileName);
        if (temp != null) {
            rsl = temp.get() == null;
        }
        return rsl;
    }

}