package com.example.backend.service;

import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        Student validatedStudent = Objects.requireNonNull(student, "student must not be null");
        return studentRepository.save(validatedStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Long validatedId = Objects.requireNonNull(id, "id must not be null");
        Student validatedStudent = Objects.requireNonNull(student, "student must not be null");

        Optional<Student> existing = studentRepository.findById(validatedId);

        if (existing.isEmpty()) {
            return null;
        }

        Student studentToUpdate = existing.get();
        studentToUpdate.setName(validatedStudent.getName());
        studentToUpdate.setEmail(validatedStudent.getEmail());
        studentToUpdate.setCourse(validatedStudent.getCourse());

        return studentRepository.save(studentToUpdate);
    }

    @Override
    public boolean deleteStudent(Long id) {
        Long validatedId = Objects.requireNonNull(id, "id must not be null");

        if (!studentRepository.existsById(validatedId)) {
            return false;
        }

        studentRepository.deleteById(validatedId);
        return true;
    }
}
