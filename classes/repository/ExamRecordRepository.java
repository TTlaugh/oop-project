package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.exam.ExamRecord;
import classes.subject.Subject;
import classes.util.Date;

public class ExamRecordRepository {

	private String baseDir;
	private Subject subject;
	private String clazz;
	private Date date;
	private String path;

	public ExamRecordRepository(String baseDir, Subject subject, String clazz, Date date) {
		this.baseDir = baseDir;
		this.subject = subject;
		this.clazz = clazz;
		this.date = date;
		this.path = baseDir + "/" + subject.getId() + "/" + clazz + "/" + date;
		createDir();
	}

	public void addExamRecord(ExamRecord examRecord, String examRecordFileName) {
		createFile(this.path + examRecordFileName);
		try {
			FileWriter writer = new FileWriter(this.path + examRecordFileName);
			writer.write(examRecord.examRecordHeader());
			for (int i = 0; i < examRecord.getMarks().size(); i++) {
				writer.write((i + 1) + "\t" + examRecord.getMarks().get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public boolean removeExamRecord(String examRecordFileName) {
		return new File(this.path + examRecordFileName).delete();
	}

	public void listAllExamRecordsCreated() {
		final File folder = new File(this.path);
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getName());
		}
	}

	public void displayExamRecord(String examRecordFileName) {
		try {
			final File file = new File(this.path + examRecordFileName);
			Scanner scanner = new Scanner(file);
			System.out.println("Exam ID: " + scanner.nextLine());
			System.out.println("Student ID: " + scanner.nextLine());
			System.out.println("Score: " + scanner.nextLine());
			System.out.println("Time taken: " + scanner.nextLine());
			System.out.println("Question\tMark");
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private boolean createDir() {
		File dir = new File(this.path);
		if (!dir.exists()) {
			dir.mkdirs();
			return true;
		}
		return false;
	}

	private boolean createFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
				return true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
