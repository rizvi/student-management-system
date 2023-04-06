package com.student.system;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.student.system.model.Student;
import com.student.system.service.StudentManagementSystemService;
import com.student.system.service.StudentManagementSystemServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) throws CsvException, IOException {
		StudentManagementSystemService smsService = new StudentManagementSystemServiceImpl();

		Student student = new Student();
		Scanner input = new Scanner(System.in);
		int option = 0;

		do {
			menu();
			option = input.nextInt();
			switch (option) {
				//1: Add Student
				case 1:

					System.out.print("What is the Student id Number ? ");
					Long sid = input.nextLong();
					input.nextLine();

					System.out.print("What is the Student Name ? ");
					String name = input.nextLine();

					System.out.print("What is the Student contact Number ? ");
					String ContactNumber = input.nextLine();

					System.out.print("What is the Student gender(M/F) ? ");
					String gender = input.nextLine();


					student.setName(name);
					student.setSid(sid);
					student.setGender(gender);
					student.setContactNo(ContactNumber);
					student = smsService.saveStudent(student);
					break;


				// 2: Delete Student
				case 2:
					System.out.print("What is the Student id number ? ");
					Long studentID = input.nextLong();
					smsService.deleteStudentById(studentID);
					break;

				// 3: Update Student
				case 3:
					System.out.print("What is the Student id number? ");
					Long stdID = input.nextLong();
					smsService.updateStudentById(student, stdID);
					break;

				// 4: Search Student
				case 4:
					System.out.print("What is the Student id ? ");
					Long studID = input.nextLong();
					Student student1 = smsService.findStudentById(studID);
					if (null == student1) {
						System.out.println("Student id does not exist\n");
					}
					break;


				// 5: Display Students
				case 5:
					System.out.print("What is the Student id ? ");
					Long stdID2 = input.nextLong();
					Student student2 = smsService.findStudentById(stdID2);
					System.out.println(student2.toString());
					break;

				//9: Exit program
				case 9:
					System.out.println("\nThank you for using the program. Goodbye!\n");
					System.exit(0);
					break;

				default:
					System.out.println("\nInvalid input\n");
					break;
			}
		}
		while (option != 9);
	}
	public static void menu()
	{
		System.out.println("MENU");
		System.out.println("1: Add Student");
		System.out.println("2: Delete Student");
		System.out.println("3: Update Student");
		System.out.println("4: Search Student");
		System.out.println("5: Display Students");
		System.out.println("9: Exit program");
		System.out.print("Enter your selection : ");
	}
}
