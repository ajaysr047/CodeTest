package com.au.compiler;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    private String registerNumber;
    private String pass;

    public Student(){}

    public Student(String registerNumber, String pass) {
        this.registerNumber = registerNumber;
        this.pass = pass;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
