package ru.job4j.exam.task.models;

public class CameraInfo {
    private int id;
    private String urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public CameraInfo(int id, String urlType, String videoUrl, String value, int ttl) {
        this.id = id;
        this.urlType = urlType;
        this.videoUrl = videoUrl;
        this.value = value;
        this.ttl = ttl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

}