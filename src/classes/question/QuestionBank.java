package classes.question;

import classes.subject.*;

import java.util.ArrayList;

public class QuestionBank {

	private Subject subject;
	private ArrayList<Question> arr;

	public QuestionBank(String subjectId) {
		this.subject = new Subject(subjectId);
		this.arr = new ArrayList<Question>();
	}

	public QuestionBank(Subject subject, ArrayList<Question> arr) {
		this.subject = subject;
		this.arr = arr;
	}

	public QuestionBank() {
		this.subject = new Subject();
		this.arr = new ArrayList<Question>();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public ArrayList<Question> getQuesList() {
		return arr;
	}

	public void setQuesList(ArrayList<Question> arr) {
		this.arr = arr;
	}
	
	public void add(Question ques) {
		this.arr.add(ques);
	}
	public void remove(int index) {
		this.arr.remove(index);
	}
	public Question get(int index) {
		return this.arr.get(index);
	}
	public void set(int index, Question ques) {
		this.arr.set(index, ques);
	}
	public void clear() {
		this.arr.clear();
	}
	public int size() {
		return this.arr.size();
	}
	public int findIndex(Question ques) {
		return this.arr.indexOf(ques);
	}
	public int findIndex(String question) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).getContent().equals(question))
				return i;
		return -1; 	
	}
	public boolean isQuestionAdded(Question ques) {
		return this.arr.contains(ques);
	}

}
