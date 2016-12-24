package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Program extends ListOfStudents {
    private String title;
    private String author;
    private String lastModified;
    private int duration;
    private ArrayList<Course>courses;
    private GregorianCalendar calenderTime;

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
        this.calenderTime=parseDate(lastModified);
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

    public Program() {
    }

    public Program(String title, String author, String lastModified, int duration, ArrayList<Course> courses) {
        this.title = title;
        this.author = author;
        this.lastModified = lastModified;
        this.duration = duration;
        this.courses = courses;
    }

    public String getText(){
        StringBuilder stringCourses=new StringBuilder();
        for (Course course : courses) {
            stringCourses.append(course.getTitle() + "\n");
        }
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        return "Title: "+title+"\n"+
                "Author: "+author+"\n"+
                "Last modified: "+dateFormat.format(calenderTime.getTime())+"\n"+
                "Duration (hrs): "+duration+"\n"+
                "Courses: "+"\n"+
                stringCourses.toString();
    }

    @Override
    public String toString() {
        return title;
    }

    private GregorianCalendar parseDate(String date){
        String[] tmp=date.split("-");
        return new GregorianCalendar(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])-1,Integer.parseInt(tmp[2]));
    }
}
