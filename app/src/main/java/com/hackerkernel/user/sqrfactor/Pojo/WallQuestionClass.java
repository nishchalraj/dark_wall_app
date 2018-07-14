package com.hackerkernel.user.sqrfactor.Pojo;

public class WallQuestionClass {
    private String description;
    private String announcedBy;
    private String id;

    public WallQuestionClass(String description, String announcedBy, String id) {
        this.description = description;
        this.announcedBy = announcedBy;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnouncedBy() {
        return announcedBy;
    }

    public void setAnnouncedBy(String announcedBy) {
        this.announcedBy = announcedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
