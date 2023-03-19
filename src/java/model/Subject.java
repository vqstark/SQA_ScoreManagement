/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Subject {
    private String id;
    private String name;
    private double tc;
    
    private double cc_percent;
    private double kt_percent;
    private double th_percent;
    private double bt_percent;
    private double thi_percent;

    public Subject(String id, String name, double tc, double cc_percent, double kt_percent, double th_percent, double bt_percent, double thi_percent) {
        this.id = id;
        this.name = name;
        this.tc = tc;
        this.cc_percent = cc_percent;
        this.kt_percent = kt_percent;
        this.th_percent = th_percent;
        this.bt_percent = bt_percent;
        this.thi_percent = thi_percent;
    }

    public Subject() {
    }
    
    

    public double getCc_percent() {
        return cc_percent;
    }

    public void setCc_percent(double cc_percent) {
        this.cc_percent = cc_percent;
    }

    public double getKt_percent() {
        return kt_percent;
    }

    public void setKt_percent(double kt_percent) {
        this.kt_percent = kt_percent;
    }

    public double getTh_percent() {
        return th_percent;
    }

    public void setTh_percent(double th_percent) {
        this.th_percent = th_percent;
    }

    public double getBt_percent() {
        return bt_percent;
    }

    public void setBt_percent(double bt_percent) {
        this.bt_percent = bt_percent;
    }

    public double getThi_percent() {
        return thi_percent;
    }

    public void setThi_percent(double thi_percent) {
        this.thi_percent = thi_percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTc() {
        return tc;
    }

    public void setTc(double tc) {
        this.tc = tc;
    }
    
    
}
