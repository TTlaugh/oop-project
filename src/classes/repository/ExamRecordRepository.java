package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.exam.ExamRecord;
import classes.subject.Subject;
import classes.util.Date;
import classes.util.FileHandling;

public class ExamRecordRepository extends FileHandling {

	private String baseDir;
	private Subject subject;
	private String clazz;
	private Date date;

	public ExamRecordRepository(String baseDir, Subject subject, String clazz, Date date) {
		super(baseDir + "/" + subject.getId() + "/" + clazz + "/" + date);
		this.baseDir = baseDir;
		this.subject = subject;
		this.clazz = clazz;
		this.date = date;
		if (!createDir()) {
			this.baseDir = null;
			this.subject = null;
			this.clazz = null;
			this.date = null;
			super.setPath(null);
		}
	}

	public boolean addExamRecord(ExamRecord examRecord, String examRecordFileName) {
		createFile(getPath() + examRecordFileName, getPath() + examRecordFileName + "_old");
		try {
			FileWriter writer = new FileWriter(getPath() + examRecordFileName);
			writer.write(examRecord.header());
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
		return removeFile(examRecordFileName);
	}

	public void listAllExamRecordsCreated() {
		listFileInDir();
	}

	@Override
	protected boolean displayContent(String fileName) {
		try {
			final File file = new File(getPath() + fileName);
			if (!file.exists())
				return false;
			try (Scanner scanner = new Scanner(file)) {
				System.out.println("Exam ID: " + scanner.nextLine());
				System.out.println("Student ID: " + scanner.nextLine());
				System.out.println("Score: " + scanner.nextLine());
				System.out.println("Time taken: " + scanner.nextLine());
				System.out.println("Question\tMark");
				while (scanner.hasNextLine()) {
					String data = scanner.nextLine();
					System.out.println(data);
				}
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean displayExamRecord(String examRecordFileName) {
		return displayContent(examRecordFileName);
	}

	public boolean displaySummaryResults() {
		final File folder = new File(getPath());
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

}
