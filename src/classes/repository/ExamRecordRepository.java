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
		if (!createDir()) {
			this.baseDir = null;
			this.subject = null;
			this.clazz = null;
			this.date = null;
			this.path = null;
		}
	}

	public boolean addExamRecord(ExamRecord examRecord, String examRecordFileName) {
		createFile(this.path + examRecordFileName);
		try {
			FileWriter writer = new FileWriter(this.path + examRecordFileName);
			writer.write(examRecord.examRecordHeader());
			for (int i = 0; i < examRecord.getMarks().size(); i++) {
				writer.write((i + 1) + "\t" + examRecord.getMarks().get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
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

	public boolean displayExamRecord(String examRecordFileName) {
		try {
			final File file = new File(this.path + examRecordFileName);
			if (!file.exists())
				return false;
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
			return false;
		}
		return true;
	}

	public boolean displaySummaryResults() {
		final File folder = new File(this.path);
		for (final File fileEntry : folder.listFiles()) {
			try {
				Scanner scanner = new Scanner(fileEntry);
				int count = 0;
				String data;
				while ((data = scanner.nextLine()) != null && count++ != 2)
					System.out.print(data + " ");
				System.out.println();
				scanner.close();
			} catch (FileNotFoundException e) {
				return false;
			}
		}
		return true;
	}

	private boolean createDir() {
		File dir = new File(this.path);
		if (!dir.exists())
			return dir.mkdirs();
		return false;
	}

	private boolean createFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists())
				return file.createNewFile();
		} catch (IOException e) {
			return false;
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
