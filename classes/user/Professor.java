package classes.user;

import classes.subject.*;

public class Professor extends Account {

	private String faculty;
	private Subject subject;
	private int ornum;
	
	public Professor() {
		super();
		this.subject = new Subject();
	}
	public Professor(String username, String password, String role, UserInfo info) {
		super(username, password, role, info);
		/*
		 * Professor: <faculty><subjectId><count>
		 * Example: IT001001 -> IT, 001, 001
		 */
		this.faculty = username.replaceAll("[^A-Za-z]+", "");
		char tmp[] = (username.replaceAll("[^0-9]", "")).toCharArray();
		this.subject = new Subject(String.copyValueOf(tmp, 0, 3));
		this.ornum = Integer.parseInt(String.copyValueOf(tmp, 3, 3));
	}
	public Professor(Professor professor) {
		super(professor.getUsername(), professor.getPassword(), professor.getRole(), professor.getInfo());
		this.faculty = professor.getFaculty();
		this.subject = professor.getSubject();
		this.ornum = professor.getOrnum();
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getOrnum() {
		return ornum;
	}

	public void setOrnum(int ornum) {
		this.ornum = ornum;
	}

	@Override
	public String toString() {
		return super.toString() + "\nProfessorInfo [faculty=" + faculty + ", subject=" + subject + ", ornum=" + ornum;
	}

}
