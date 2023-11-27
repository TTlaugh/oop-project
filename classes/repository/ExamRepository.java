package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.exam.Exam;
import classes.question.Question;
import classes.subject.Subject;
import classes.util.Date;

public class ExamRepository {

	private String baseDir;
	private Subject subject;
	private String clazz;
	private Date date;
	private String path;

	public ExamRepository(String baseDir, Subject subject, String clazz, Date date) {
		this.baseDir = baseDir;
		this.subject = subject;
		this.clazz = clazz;
		this.date = date;
		this.path = baseDir + "/" + subject.getId() + "/" + clazz + "/" + date + "/";
		createDir();
	}

	public void createExams(Exam exam, int numOfExams, int numOfExamId) {
		int examsPerId = numOfExams / numOfExamId;
		int count = 0;
		for (int i = 1; i <= numOfExamId; i++) {
			if (i == numOfExamId)
				examsPerId = numOfExams - count;
			for (int j = 0; j < examsPerId; j++) {
				exam.setId(String.valueOf(i));
				exam.getQuestions().shuffleQuestionSet();
				String fileName = this.path + "exam" + String.format("%03d", count++);
				createFile(fileName);
				try {
					FileWriter writer = new FileWriter(fileName);
					writer.write(exam.examHeader());
					for (Question ques : exam.getQuestions().getQuesSet()) {
						writer.write(ques.questionDisplay());
					}
					writer.close();
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
		}
	}

	public void addExam(Exam exam, String examFileName) {
		createFile(this.path + examFileName);
		try {
			FileWriter writer = new FileWriter(this.path + examFileName);
			writer.write(exam.examHeader());
			for (Question ques : exam.getQuestions().getQuesSet()) {
				writer.write(ques.questionDisplay());
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public boolean removeExam(String examFileName) {
		return new File(this.path + examFileName).delete();
	}

	public void listAllExamsCreated() {
		final File folder = new File(this.path);
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getName());
		}
	}

	public void previewExam(String examFileName) {
		try {
			final File file = new File(this.path + examFileName);
			Scanner scanner = new Scanner(file);
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
