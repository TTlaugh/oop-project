package classes.exam;

import classes.question.*;

import classes.util.*;

public class Exam {
	private int id;
	private String name;
	private String description;
	private Date date;
	private int time;
	private int passingScore;
	private QuestionSet questions;

	public Exam(int id, String name, String description, Date date, int time, int passingScore, QuestionSet questions) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.time = time;
		this.passingScore = passingScore;
		this.questions = questions;
	}
	public Exam() {
		this.id = 0;
		this.name = null;
		this.description = null;
		this.date = null;
		this.time = 0;
		this.passingScore = 0;
		this.questions = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getPassingScore() {
		return passingScore;
	}
	public void setPassingScore(int passingScore) {
		this.passingScore = passingScore;
	}
	public QuestionSet getQuestions() {
		return questions;
	}
	public void setQuestions(QuestionSet questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", time="
				+ time + ", passingScore=" + passingScore + "]";
	}

}
