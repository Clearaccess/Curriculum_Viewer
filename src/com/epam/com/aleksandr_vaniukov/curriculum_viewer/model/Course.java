package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.util.ArrayList;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Course {
    private String title;
    private String author;
    private String lastModified;
    private int duration;
    private ArrayList<Task>tasks;

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Course(String title, String author, String lastModified, int duration, ArrayList<Task> tasks) {
        this.title = title;
        this.author = author;
        this.lastModified = lastModified;
        this.duration = duration;
        this.tasks = tasks;
    }
}
