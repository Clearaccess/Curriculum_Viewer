package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.util.ArrayList;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Program {
    private String title;
    private String author;
    private String latsModified;
    private int duration;
    private ArrayList<Course>courses;

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

    public String getLatsModified() {
        return latsModified;
    }

    public void setLatsModified(String latsModified) {
        this.latsModified = latsModified;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public Program(String title, String author, String latsModified, int duration, ArrayList<Course> courses) {
        this.title = title;
        this.author = author;
        this.latsModified = latsModified;
        this.duration = duration;
        this.courses = courses;
    }
}
