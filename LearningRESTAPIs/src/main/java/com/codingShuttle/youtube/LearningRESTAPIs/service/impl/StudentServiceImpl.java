package com.codingShuttle.youtube.LearningRESTAPIs.service.impl;

import com.codingShuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingShuttle.youtube.LearningRESTAPIs.DTO.StudentDto;
import com.codingShuttle.youtube.LearningRESTAPIs.entity.Student;
import com.codingShuttle.youtube.LearningRESTAPIs.repository.StudentRepository;
import com.codingShuttle.youtube.LearningRESTAPIs.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found by id " + id));

        return new StudentDto(student.getId(), student.getName(), student.getEmail());
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student savedStudent = studentRepository.save(newStudent);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist by id: " + id);
        }
        studentRepository.deleteById(id);
}
}
