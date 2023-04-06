package com.student.system.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.student.system.model.Student;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
	private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";
	private static final String STUDENT_CSV = "./student.csv";
	private static final String TEACHER_CSV = "./teacher.csv";
	private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";

	public static void writeFromArrayOfStrings() throws IOException {
		try (
				Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));

				CSVWriter csvWriter = new CSVWriter(writer,
						CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER,
						CSVWriter.DEFAULT_LINE_END);
		) {
			String[] headerRecord = {"Name", "Sid", "Gender", "Country"};
			csvWriter.writeNext(headerRecord);

			csvWriter.writeNext(new String[]{"Sundar Pichai ♥", "sundar.pichai@gmail.com", "+1-1111111111", "India"});
			csvWriter.writeNext(new String[]{"Satya Nadella", "satya.nadella@outlook.com", "+1-1111111112", "India"});
		}
	}

/*	public static void saveStudent(Student student) throws IOException,
			CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException {

		try (
				Writer writer = Files.newBufferedWriter(Paths.get(STUDENT_CSV));
		) {
			StatefulBeanToCsv<Student> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.build();
			List<Student> studenList = new ArrayList<>();
			System.out.println("Before Save: "+student.toString());
			studenList.add(student);

			beanToCsv.write(studenList);
		}
	}*/
public static void saveStudent(Student student) throws IOException,
		CsvDataTypeMismatchException,
		CsvRequiredFieldEmptyException {

	try (
			Writer writer = Files.newBufferedWriter(Paths.get(STUDENT_CSV));
	) {
		StatefulBeanToCsv<Student> beanToCsv = new StatefulBeanToCsvBuilder(writer)
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.build();
		List<Student> studenList = new ArrayList<>();
		System.out.println("Before Save: "+student.toString());
		studenList.add(student);

		beanToCsv.write(studenList);
	}
}

}
