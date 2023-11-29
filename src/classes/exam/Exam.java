package classes.exam;

import classes.question.QuestionSet;
import classes.subject.Subject;
import classes.util.Date;

public class Exam {

	private String id;
	private String name;
	private Subject subject;
	private String note;
	private Date date;
	private int time;
	private QuestionSet questions;

	public Exam(String id, String name, Subject subject, String note, Date date, int time, QuestionSet questions) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.note = "(" + note + ")";
		this.date = date;
		this.time = time;
		this.questions = questions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return note;
	}

	public void setDescription(String description) {
		this.note = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public QuestionSet getQuestions() {
		return questions;
	}

	public void setQuestions(QuestionSet questions) {
		this.questions = questions;
	}

	public String examHeader() {
		return id + "\n" + name + "\n" + subject.getName() + "\n" + date.toString() + "\n" + time + "\n" + note + "\n";
	}

}
