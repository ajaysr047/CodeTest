package com.au.compiler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    private int qNo;
    private String question;
    @Column(length=2000)private String baseCode;
    @Column(length=2000)private String sampleTestRunner;
    @Column(length=2000)private String testRunner;

    public Question(){}

    public Question(int qNo, String question, String baseCode, String sampleTestRunner, String testRunner) {
        this.qNo = qNo;
        this.question = question;
        this.baseCode = baseCode;
        this.sampleTestRunner = sampleTestRunner;
        this.testRunner = testRunner;
    }

    public int getqNo() {
        return qNo;
    }

    public void setqNo(int qNo) {
        this.qNo = qNo;
    }

    public String getSampleTestRunner() {
        return sampleTestRunner;
    }

    public void setSampleTestRunner(String sampleTestRunner) {
        this.sampleTestRunner = sampleTestRunner;
    }

    public String getTestRunner() {
        return testRunner;
    }

    public void setTestRunner(String testRunner) {
        this.testRunner = testRunner;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
}
