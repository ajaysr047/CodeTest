package com.au.compiler;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodeResult {
    @Id
    private String registerNumberAndQuestionNumber; // format: <registerNumber+questionNumber>
    private String question;
    private String result;

    public CodeResult(){}

    public CodeResult(String registerNumberAndQuestionNumber, String question, String result) {
        this.registerNumberAndQuestionNumber = registerNumberAndQuestionNumber;
        this.question = question;
        this.result = result;
    }

    public String getRegisterNumberAndQuestionNumber() {
        return registerNumberAndQuestionNumber;
    }

    public void setRegisterNumberAndQuestionNumber(String registerNumberAndQuestionNumber) {
        this.registerNumberAndQuestionNumber = registerNumberAndQuestionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
