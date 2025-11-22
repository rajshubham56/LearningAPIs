package com.codingShuttle.youtube.LearningRESTAPIs.controller;

import com.codingShuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingShuttle.youtube.LearningRESTAPIs.DTO.StudentDto;
import com.codingShuttle.youtube.LearningRESTAPIs.entity.Student;
import com.codingShuttle.youtube.LearningRESTAPIs.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody AddStudentRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createNewStudent(requestDto));
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }





}





