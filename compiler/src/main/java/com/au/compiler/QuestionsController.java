package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class QuestionsController {

    @Autowired
    QuestionRepo questionRepo;

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    public List<Question> getQuestion(){
        List<Question> questions = new ArrayList<>();
        questionRepo.findAll().forEach(questions::add);
        return questions;
//        return new Question(1,"Return the string Hello world", "class Program {\n public static String print() {\n /* Write your code here. Do not modify this function definition */ \n} }", "class ProgramRunner {\n public static void main(String args[])throws Exception {\n Program obj = new Program();\n String s = obj.print();\n if(s.equals(\"Hello world\"))\n System.out.println(\"Sample test passed!\");\n else \nSystem.out.println(\"Sample test failed!\");\n \n } }", "class ProgramRunner {\n public static void main(String args[])throws Exception {\n Program obj = new Program();\n String s = obj.print();\n \n if(s.equals(\"Hello world\"))\n System.out.println(\"Main test passed!\");\n else \nSystem.out.println(\"Main test failed!\");\n } }");
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(@RequestBody Question question){
            questionRepo.save(question);
        return "added!";
    }

    @RequestMapping(value = "/removeQuestion", method = RequestMethod.POST)
    public String removeQuestion(@RequestBody QuestionNumber questionNumber)
    {
        questionRepo.deleteById(questionNumber.getqNo());

        return "Removed!";
    }

    @RequestMapping(value = "/removeAllQuestion", method = RequestMethod.GET)
    public String removeAllQuestion()
    {
        questionRepo.deleteAll();

        return "Removed!";
    }
}
