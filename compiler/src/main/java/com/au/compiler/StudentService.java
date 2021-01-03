package com.au.compiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public void addStudent(Student student)
    {
        studentRepo.save(student);
    }

    public List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();
        studentRepo.findAll()
                .forEach(students::add);
        return students;
    }

    public Optional<Student> findStudent(String id)
    {
        return studentRepo.findById(id);
    }

    public void removeStudent(String registerNumber)
    {
        studentRepo.deleteById(registerNumber);
    }

    public void removeAllStudent()
    {
        studentRepo.deleteAll();
    }

    public void addStudentRange(RegisterNumberRange registerNumberRange) {
        int from = registerNumberRange.getFromRegisterNumber(), to = registerNumberRange.getToRegisterNumber();
        for(; from <= to; from++)
        {
            studentRepo.save(new Student(String.valueOf(from), String.valueOf(from%10000)));
        }
    }
}
