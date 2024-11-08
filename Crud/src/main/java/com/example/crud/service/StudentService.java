package com.example.crud.service;

import com.example.crud.entities.Student;
import com.example.crud.exceptions.ResourceNotFoundException;
import com.example.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // Create a single student
    public Student createStudent(Student student, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            student.setImage(imageFile.getBytes());
        }
        return studentRepository.save(student);
    }


    // Create multiple students
    public List<Student> createStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Retrieve a student by ID
    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Update a student by ID
    public Student updateStudent(long id, Student studentDetails) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setFirstName(studentDetails.getFirstName());
            existingStudent.setLastName(studentDetails.getLastName());
            existingStudent.setPhoneNo(studentDetails.getPhoneNo());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Delete a student by ID
    public void deleteStudentById(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
    }
}
