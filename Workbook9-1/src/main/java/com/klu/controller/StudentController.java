package com.klu.controller;

import com.klu.model.Student;
import com.klu.exception.StudentNotFoundException;
import com.klu.exception.InvalidInputException;

import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id) {

        int studentId;

        try {
            studentId = Integer.parseInt(id);
        } 
        catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number.");
        }

        if (studentId == 1) {
            return new Student(1, "Rahul", "CSE");
        } 
        else if (studentId == 2) {
            return new Student(2, "Priya", "ECE");
        } 
        else if(studentId <= 0) {
            throw new InvalidInputException("Invalid Student ID");
        }
        else {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
        }
    }

}