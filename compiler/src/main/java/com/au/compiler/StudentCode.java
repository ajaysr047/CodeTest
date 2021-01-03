package com.au.compiler;

public class StudentCode {

    private String registerNumber;
    private String code;
    private String fileName;
    private String questionNumber;
    private String question;

    public StudentCode() {
    }

    public StudentCode(String registerNumber, String code, String fileName, String questionNumber, String question) {
        this.registerNumber = registerNumber;
        this.code = code;
        this.fileName = fileName;
        this.questionNumber = questionNumber;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
