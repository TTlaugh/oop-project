package classes.repository;

import java.io.FileWriter;
import java.io.IOException;

import classes.exam.Exam;
import classes.question.Question;
import classes.subject.Subject;
import classes.util.Date;
import classes.util.FileHandling;

public class ExamRepository extends FileHandling {

	private String baseDir;
	private Subject subject;
	private String clazz;
	private Date date;

	public ExamRepository(String baseDir, Subject subject, String clazz, Date date) {
		super(baseDir + "/" + subject.getId() + "/" + clazz + "/" + date + "/");
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

	public boolean createExams(Exam exam, int numOfExams, int numOfExamId) {
		int examsPerId = numOfExams / numOfExamId;
		int count = 0;
		for (int i = 1; i <= numOfExamId; i++) {
			if (i == numOfExamId)
				examsPerId = numOfExams - count;
			for (int j = 0; j < examsPerId; j++) {
				exam.setId(String.valueOf(i));
				exam.getQuestions().shuffleQuestionSet();
				String fileName = getPath() + "exam" + String.format("%03d", count++);
				createFile(fileName, fileName + "_old");
				try {
					FileWriter writer = new FileWriter(fileName);
					writer.write(exam.header());
					for (Question ques : exam.getQuestions().getQuesSet()) {
						writer.write(ques.questionDetail());
					}
					writer.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean addExam(Exam exam, String examFileName) {
		createFile(getPath() + examFileName, getPath() + examFileName + "_old");
		try {
			FileWriter writer = new FileWriter(getPath() + examFileName);
			writer.write(exam.header());
			for (Question ques : exam.getQuestions().getQuesSet()) {
				writer.write(ques.questionDetail());
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean removeExam(String examFileName) {
		return removeFile(examFileName);
	}

	public void listAllExamsCreated() {
		listFileInDir();
	}

	public boolean previewExam(String examFileName) {
		return displayContent(examFileName);
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
