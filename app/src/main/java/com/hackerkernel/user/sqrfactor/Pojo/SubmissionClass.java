package com.hackerkernel.user.sqrfactor.Pojo;

public class SubmissionClass {
    private String id;
    private String title;
    private String code;
    private String coverUrl;

    public SubmissionClass(String id, String title, String code, String coverUrl) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.coverUrl = coverUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
