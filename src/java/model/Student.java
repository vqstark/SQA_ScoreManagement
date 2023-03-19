/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Student {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String clas;
    
    public Student() {
    }

    public Student(int id, String username, String password, String fullName, String clas) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.clas = clas;
    }
    
    public Student(int id, String username, String fullName, String clas) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.clas = clas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Student(String username, String fullName, String clas) {
        this.username = username;
        this.fullName = fullName;
        this.clas = clas;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
    
    
}
