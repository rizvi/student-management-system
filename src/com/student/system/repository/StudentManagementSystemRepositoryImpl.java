package com.student.system.repository;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.student.system.model.Admin;
import com.student.system.model.Student;
import com.student.system.model.Teacher;
import com.student.system.util.FileUtil;
import com.student.system.util.OpenCSVWriter;

import java.io.IOException;
import java.util.List;

public class StudentManagementSystemRepositoryImpl implements StudentManagementSystemRepository {
	OpenCSVWriter csvWriter = new OpenCSVWriter();
	FileUtil fileUtil = new FileUtil();
/*	@Override
	public Student saveStudent(Student student) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
		csvWriter.saveStudent(student);
		return student;
	}*/
@Override
public Student saveStudent(Student student) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
	fileUtil.saveStudent(student);
	return student;
}
	@Override
	public Student findStudentById(Long id) {
		return fileUtil.getStudentById(id);
	}

	@Override
	public void deleteStudentById(Long id) throws IOException, CsvException {
		fileUtil.deleteStudent(id);
	}

	@Override
	public Student updateStudentById(Student student, Long id) {
		return null;
	}

	@Override
	public List<Student> getStudentList() {
		return null;
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		return FileUtil.addTeacher(teacher);
	}

	@Override
	public Admin getAdminDetails(Long id) {
		return null;
	}

	@Override
	public Student updateStudentNameById(String name, Long id) throws IOException, CsvException {
		Student student = fileUtil.getStudentById(id);
		student.setName(name);
		fileUtil.deleteStudent(id);
		fileUtil.saveStudent(student);
		return student;
	}
}
