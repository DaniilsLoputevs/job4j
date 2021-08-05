package ru.job4j.nonblockalgoritm.cache.model;

import ru.job4j.nonblockalgoritm.cache.CacheModel;

public class CacheTypeOne implements CacheModel<String> {
    private String content;

    public CacheTypeOne(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}