package com.epam.com.aleksandr_vaniukov.curriculum_viewer.model;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandr_Vaniukov on 12/22/2016.
 */
public class Student extends ListOfStudents {
    private String fullName;
    private String email;
    private String region;
    private String contractSigned;
    private String startDate;
    private Program program;
    private GregorianCalendar timeStart;
    private GregorianCalendar timeEnd;

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
        this.timeStart=parseDate(startDate);
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
        this.timeEnd=calculateFinishCourse(timeStart,program.getDuration());
    }

    public Student() {
    }

    public Student(String fullName, String email, String region, String contractSigned, String startDate, Program program) {
        this.fullName = fullName;
        this.email = email;
        this.region = region;
        this.contractSigned = contractSigned;
        this.startDate = startDate;
        this.program = program;
    }

    public String getText(){
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        return  "Full Name: "+ fullName+"\n"+
                "E-mail: "+ email+"\n"+
                "Region: "+ region+"\n"+
                "Contract Signed: "+ contractSigned+"\n"+
                "Start Date: "+ dateFormat.format(timeStart.getTime())+"\n"+
                "Date of finish course: "+ dateFormat.format(timeEnd.getTime())+"\n";

    }
    @Override
    public String toString() {
        return fullName;
    }

    private GregorianCalendar parseDate(String date){
        String[] tmp=date.split("-");
        return new GregorianCalendar(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])-1,Integer.parseInt(tmp[2]));
    }

    private GregorianCalendar calculateFinishCourse(GregorianCalendar timeStart,int duration){

        GregorianCalendar tmp=new GregorianCalendar(timeStart.get(Calendar.YEAR),timeStart.get(Calendar.MONTH),timeStart.get(Calendar.DAY_OF_MONTH));

        if(duration<=0){
            return tmp;
        }

        int countDays=0;
        int countHours=Math.max(duration-8,0);
        //Определяем кол-во рабочих дней до конца текущей недели и вычитаем часы
        if(1<tmp.get(Calendar.DAY_OF_WEEK)
                &&
                tmp.get(Calendar.DAY_OF_WEEK)<7){

            if(countHours>=Math.max((7- tmp.get(Calendar.DAY_OF_WEEK)-1),0)*8){

                countHours-=Math.max((7- tmp.get(Calendar.DAY_OF_WEEK)-1),0)*8;
                countDays+=Math.max((7- tmp.get(Calendar.DAY_OF_WEEK)-1),0);
            }
            else{
                countDays += ((countHours % 8 == 0) ? countHours / 8 : countHours / 8 + 1);
                countHours=0;
            }

            //Проверяем управились ли мы в первую неделю, если не добавляем 2 дня
            if(countHours>0){
                countDays+=2;
            }
        }

        //Определяем кол-во полных недель
        int countWeeks=countHours/8/5;
        countHours-=countWeeks*5*8;
        countDays+=countWeeks*7;

        //Смотрим остаток
        if(countHours>0){
            countDays+=((countHours%8==0)?countHours/8:countHours/8+1);
            countHours=0;
        }

        tmp.add(Calendar.DAY_OF_MONTH,countDays);
        return tmp;
    }
}
