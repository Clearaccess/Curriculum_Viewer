package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.util.ArrayList;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class DataStudents {
    private ArrayList<Student>students;

    public DataStudents(){
        this.students= new ArrayList<>();
    }

    public void addStudent(Student std){
        students.add(std);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
