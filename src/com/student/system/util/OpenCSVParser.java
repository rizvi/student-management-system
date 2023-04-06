package com.student.system.util;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.student.system.model.Student;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class OpenCSVParser {
	private static final String SAMPLE_CSV_FILE_PATH = "./users-with-header.csv";

	public static void main(String[] args) throws IOException {

		try (
				Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
		) {
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Student.class);
			String[] memberFieldsToBindTo = {"name", "email", "phoneNo", "country"};
			strategy.setColumnMapping(memberFieldsToBindTo);

			CsvToBean<Student> csvToBean = new CsvToBeanBuilder(reader)
					.withMappingStrategy(strategy)
					.withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			Iterator<Student> myUserIterator = csvToBean.iterator();

			while (myUserIterator.hasNext()) {
				Student student = myUserIterator.next();
				System.out.println("Name : " + student.getName());
				System.out.println("ID : " + student.getSid());
				System.out.println("Contact Number : " + student.getContactNo());
				System.out.println("Gender : " + student.getGender());
				System.out.println("---------------------------");
			}
		}
	}

	// Reads all CSV contents into memory (Not suitable for large CSV files)
	private static void readAllBeansAtOnce(CsvToBean csvToBean) {
		List<Student> students = csvToBean.parse();

		for (Student student : students) {
			System.out.println("Name : " + student.getName());
			System.out.println("ID : " + student.getSid());
			System.out.println("Contact Number : " + student.getContactNo());
			System.out.println("Gender : " + student.getGender());
			System.out.println("---------------------------");
		}
	}
}
