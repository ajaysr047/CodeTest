/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty;

/**
 *
 * @author ajay
 */
public class Student {

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