package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Task {
    private String title;
    private String author;
    private String lastModified;
    private int duration;
    private String type;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task(String title, String author, String lastModified, int duration, String type, String status) {
        this.title = title;
        this.author = author;
        this.lastModified = lastModified;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }
}
