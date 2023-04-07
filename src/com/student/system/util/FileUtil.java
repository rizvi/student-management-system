package com.student.system.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.student.system.model.Student;
import com.student.system.model.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	private static final String STUDENT_CSV = "./student.csv";
	private static final String TEACHER_CSV = "./teacher.csv";

	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	//Student attributes index
	private static final int STUDENT_ID_IDX = 0;
	private static final int STUDENT_NAME_IDX = 1;
	private static final int STUDENT_GENDER = 2;
	private static final int STUDENT_CONTACT_NO = 3;
	//CSV file header
	private static final String FILE_HEADER = "ID,Name,Gender,ContactNo";
	private static final String FILE_HEADER_FOR_TEACHER = "ID,Name,Mobile,Email";

	public static void saveStudent(Student student) throws IOException {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(STUDENT_CSV);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			//Write a new student object list to the CSV file
			fileWriter.append(String.valueOf(student.getSid()));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(student.getName());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(student.getGender());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(student.getContactNo());
			fileWriter.append(NEW_LINE_SEPARATOR);

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
//				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}

	public static List<Student> getStudentList() {
		BufferedReader fileReader = null;
		//Create a new list of student to be filled by CSV file data
		List students = new ArrayList();

		try {
			String line = "";

			//Create the file reader
			fileReader = new BufferedReader(new FileReader(STUDENT_CSV));

			//Read the CSV file header to skip it
			fileReader.readLine();

			//Read the file line by line starting from the second line
			while ((line = fileReader.readLine()) != null) {
				//Get all tokens available in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					//Create a new student object and fill his  data
					Student student = new Student(Long.parseLong(tokens[STUDENT_ID_IDX]),
							tokens[STUDENT_NAME_IDX],
							tokens[STUDENT_GENDER],
							tokens[STUDENT_CONTACT_NO]);
					students.add(student);
				}
			}

			//Print the new student list
			for (Object student : students) {
				System.out.println(student.toString());
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}
		return students;
	}

	public static int getRowNumberOfStudentFile(Long id) {

		BufferedReader fileReader = null;
		//Create a new list of student to be filled by CSV file data
		List students = new ArrayList();
		int count = 0;
		try {
			String line = "";

			//Create the file reader
			fileReader = new BufferedReader(new FileReader(STUDENT_CSV));

			//Read the CSV file header to skip it
			fileReader.readLine();

			//Read the file line by line starting from the second line
			while ((line = fileReader.readLine()) != null) {
				//Get all tokens available in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					//Create a new student object and fill his  data
					Student student = new Student(Long.parseLong(tokens[STUDENT_ID_IDX]),
							tokens[STUDENT_NAME_IDX],
							tokens[STUDENT_GENDER],
							tokens[STUDENT_CONTACT_NO]);
					if (Long.parseLong(tokens[STUDENT_ID_IDX]) == id) {
						count++;
						break;
					}
					students.add(student);
					count++;
				}
			}

			//Print the new student list
			for (Object student : students) {
				System.out.println(student.toString());
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}
		return count;
	}

	public void deleteStudent(Long id) throws IOException, CsvException {
		CSVReader reader2 = new CSVReader(new FileReader(STUDENT_CSV));
		List<String[]> allElements = reader2.readAll();
		int rowOfStudent = getRowNumberOfStudentFile(id);
		if (rowOfStudent > 0) {
			allElements.remove(rowOfStudent);
			System.out.println("Student id number: " + id + " is removed successfully");
		} else {
			System.out.println("Student id number: " + id + " is not enlisted");
		}
		FileWriter sw = new FileWriter(STUDENT_CSV);
		CSVWriter writer = new CSVWriter(sw);
		writer.writeAll(allElements);
		writer.close();
	}

	public void updateStudent(Student updatedStudent, Long id) throws IOException, CsvException {
		CSVReader reader2 = new CSVReader(new FileReader(STUDENT_CSV));
		List<String[]> allElements = reader2.readAll();
		int rowOfStudent = getRowNumberOfStudentFile(id);
		if (rowOfStudent > 0) {
			allElements.remove(rowOfStudent);
			System.out.println("Student id number: " + id + " is removed successfully");
		} else {
			System.out.println("Student id number: " + id + " is not enlisted");
		}
		FileWriter sw = new FileWriter(STUDENT_CSV);
		CSVWriter writer = new CSVWriter(sw);
		writer.writeAll(allElements);
		writer.close();
	}

	public Student getStudentById(Long id) {
		BufferedReader fileReader = null;
		Student student = new Student();
		try {
			String line = "";
			//Create the file reader
			fileReader = new BufferedReader(new FileReader(STUDENT_CSV));

			//Read the CSV file header to skip it
			fileReader.readLine();

			//Read the file line by line starting from the second line
			while ((line = fileReader.readLine()) != null) {
				//Get all tokens available in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					//Create a new student object and fill his  data
					if (Long.parseLong(tokens[STUDENT_ID_IDX]) == id) {
						student = new Student(Long.parseLong(tokens[STUDENT_ID_IDX]),
								tokens[STUDENT_NAME_IDX],
								tokens[STUDENT_GENDER],
								tokens[STUDENT_CONTACT_NO]);
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}
		return student;
	}

	public static Teacher addTeacher(Teacher teacher) {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(TEACHER_CSV);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER_FOR_TEACHER.toString());

			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			//Write a new teacher object list to the CSV file
			fileWriter.append(String.valueOf(teacher.getId()));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(teacher.getName());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(teacher.getMobile());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(teacher.getEmail());
			fileWriter.append(NEW_LINE_SEPARATOR);

			System.out.println("New teacher added successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return teacher;
	}
}

