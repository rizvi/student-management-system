package com.student.system.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.student.system.model.Admin;
import com.student.system.model.Student;
import com.student.system.model.Teacher;
import com.student.system.repository.StudentManagementSystemRepository;
import com.student.system.repository.StudentManagementSystemRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class StudentManagementSystemServiceImpl implements StudentManagementSystemService {

    StudentManagementSystemRepository smsRepository = new StudentManagementSystemRepositoryImpl();

    @Override
    public Student saveStudent(Student student) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        return smsRepository.saveStudent(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return smsRepository.findStudentById(id);
    }

    @Override
    public void deleteStudentById(Long id) throws IOException, CsvException {
        smsRepository.deleteStudentById(id);
    }

    @Override
    public Student updateStudentById(Student student, Long id) {
        return smsRepository.updateStudentById(student, id);
    }

    @Override
    public List<Student> getStudentList() {
        return smsRepository.getStudentList();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return smsRepository.saveTeacher(teacher);
    }

    @Override
    public Admin getAdminDetails(Long id) {
        return smsRepository.getAdminDetails(id);
    }
}
