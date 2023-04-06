package com.student.system.repository;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.student.system.model.Admin;
import com.student.system.model.Student;
import com.student.system.model.Teacher;

import java.io.IOException;
import java.util.List;

public interface StudentManagementSystemRepository {
	Student saveStudent(Student student) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;
	Student findStudentById(Long id);
	void deleteStudentById(Long id) throws IOException, CsvException;
	Student updateStudentById(Student student, Long id);
	List<Student> getStudentList();
	Teacher saveTeacher(Teacher teacher);
	Admin getAdminDetails(Long id);
	Student updateStudentNameById(String name, Long id) throws IOException, CsvException;
}
