package com.codingShuttle.youtube.LearningRESTAPIs.service;

import com.codingShuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingShuttle.youtube.LearningRESTAPIs.DTO.StudentDto;
import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id); // <-- ONLY THIS METHOD
}
