package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Task extends ListOfStudents {
    private String title;
    private String author;
    private String lastModified;
    private int duration;
    private String type;
    private String status;
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

    public Task() {
    }

    public Task(String title, String author, String lastModified, int duration, String type, String status) {
        this.title = title;
        this.author = author;
        this.lastModified = lastModified;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }

    public String getText(){
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        return "Title: "+title+"\n"+
                "Author: "+author+"\n"+
                "Last modified: "+dateFormat.format(calenderTime.getTime())+"\n"+
                "Duration (hrs): "+duration+"\n"+
                "Type: "+type+"\n"+
                "Status: "+status+"\n";
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
