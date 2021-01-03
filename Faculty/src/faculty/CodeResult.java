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
public class CodeResult {
    
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
