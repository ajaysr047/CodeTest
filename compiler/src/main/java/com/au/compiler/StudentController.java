package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
        return "Success";
    }

    @RequestMapping(value = "/addRange", method = RequestMethod.POST)
    public String addStudentRange(@RequestBody RegisterNumberRange registerNumberRange)
    {
        studentService.addStudentRange(registerNumberRange);
        return "Success";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeStudent(@RequestBody Student student)
    {
        studentService.removeStudent(student.getRegisterNumber());
        return "Removed!";
    }

    @RequestMapping(value = "/removeAll", method = RequestMethod.GET)
    public String removeAllStudent()
    {
        studentService.removeAllStudent();
        return "Removed!";
    }

    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    @RequestMapping(value = "/authStudent", method = RequestMethod.POST)
    public AuthResult authStudent(@RequestBody Student student)
    {
        Student authVal = null;
        Optional<Student> auth =  studentService.findStudent(student.getRegisterNumber());
        if(auth.isPresent())
        {
            authVal = auth.get();
            return authVal.getPass().equals(student.getPass())  ? new AuthResult("Success") : new AuthResult("Failure");
        }
        return new AuthResult("Failure");
    }
}
