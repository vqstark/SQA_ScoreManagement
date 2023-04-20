/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class Score {
    private int id;
    private Student student;
    private Semester semester;
    private Subject subject;
    
    private double cc;
    private double kt;
    private double th;
    private double bt;
    private double thi;

    public Score() {
    }

    public Score(int id, Student student, Semester semester, Subject subject, double cc, double kt, double th, double bt, double thi) {
        this.id = id;
        this.student = student;
        this.semester = semester;
        this.subject = subject;
        this.cc = cc;
        this.kt = kt;
        this.th = th;
        this.bt = bt;
        this.thi = thi;
    }
    
    public Score(int id, Subject subject, double cc, double kt, double th, double bt, double thi) {
        this.id = id;
        this.subject = subject;
        this.cc = cc;
        this.kt = kt;
        this.th = th;
        this.bt = bt;
        this.thi = thi;
    }
    
    public Score(Subject subject, double cc, double kt, double th, double bt, double thi) {
        this.subject = subject;
        this.cc = cc;
        this.kt = kt;
        this.th = th;
        this.bt = bt;
        this.thi = thi;
    }
    
    public Score(double cc, double kt, double th, double bt, double thi) {
        this.cc = cc;
        this.kt = kt;
        this.th = th;
        this.bt = bt;
        this.thi = thi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getCc() {
        return cc;
    }

    public void setCc(double cc) {
        this.cc = cc;
    }

    public double getKt() {
        return kt;
    }

    public void setKt(double kt) {
        this.kt = kt;
    }

    public double getTh() {
        return th;
    }

    public void setTh(double th) {
        this.th = th;
    }

    public double getBt() {
        return bt;
    }

    public void setBt(double bt) {
        this.bt = bt;
    }

    public double getThi() {
        return thi;
    }

    public void setThi(double thi) {
        this.thi = thi;
    }   
    
    public double getTK(){
        DecimalFormat df = new DecimalFormat("0.0");
        if(cc==0 && subject.getCc_percent()!=0.0
            || kt==0 && subject.getKt_percent()!=0.0
            || th==0 && subject.getTh_percent()!=0.0
            || bt==0 && subject.getBt_percent()!=0.0
            || thi==0 && subject.getThi_percent()!=0.0){
            return Double.parseDouble(df.format(0.0));
        }
        double tk =subject.getCc_percent()*cc/100
                +subject.getKt_percent()*kt/100
                +subject.getTh_percent()*th/100
                +subject.getBt_percent()*bt/100
                +subject.getThi_percent()*thi/100;
        return Double.parseDouble(df.format(tk));
    }
    
    public String getTKC(){
        double tk = getTK();
        if(tk<4.0){
            return "F";
        }else if(4.0<=tk && tk<=4.9){
            return "D";
        }else if(5.0<=tk && tk<=5.4){
            return "D+";
        }else if(5.5<=tk && tk<=6.4){
            return "C";
        }else if(6.5<=tk && tk<=6.9){
            return "C+";
        }else if(7.0<=tk && tk<=7.9){
            return "B";
        }else if(8.0<=tk && tk<=8.4){
            return "B+";
        }else if(8.5<=tk && tk<=8.9){
            return "A";
        }else{
            return "A+";
        }
    }
    
    public String getD(){
        if(getTK()<4.0)
            return "Chưa Đạt";
        else
            return "Đạt";
    }
}
