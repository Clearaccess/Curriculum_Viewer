package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Student {
    private String fullName;
    private String email;
    private String region;
    private String contractSigned;
    private String startDate;
    private Program program;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContractSigned() {
        return contractSigned;
    }

    public void setContractSigned(String contractSigned) {
        this.contractSigned = contractSigned;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Student(String fullName, String email, String region, String contractSigned, String startDate, Program program) {
        this.fullName = fullName;
        this.email = email;
        this.region = region;
        this.contractSigned = contractSigned;
        this.startDate = startDate;
        this.program = program;
    }
}
