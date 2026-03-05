package com.example.backend.service;

import com.example.backend.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student updateStudent(Long id, Student student);

    boolean deleteStudent(Long id);
}
