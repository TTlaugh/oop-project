package classes.exam;

import classes.question.*;
import classes.subject.Subject;
import classes.util.*;

import java.util.ArrayList;

public class Exam {

	private String id;
	private String name;
	private Subject subject;
	private String note;
	private Date date;
	private int time;
	private int passingScore;
	private ArrayList<Question> questions;

	public Exam(String id, String name, Subject subject, String description, Date date, int time, int passingScore,
			ArrayList<Question> questions) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.note = description;
		this.date = date;
		this.time = time;
		this.passingScore = passingScore;
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
	public int getPassingScore() {
		return passingScore;
	}
	public void setPassingScore(int passingScore) {
		this.passingScore = passingScore;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String examHeader() {
		return
				"Ma_De: "+id+"\n"+
				""+name+"\n"+
				"Mon: "+subject.getName()+"\n"+
				"Ngay_thi: "+date.toString()+"\n"+
				"Thoi_gian_lam_bai: "+time+"\n"+
				"Diem_thong_qua: "+passingScore+"\n"+
				"Ghi_Chu: "+note+"\n"
		;
	}

}
