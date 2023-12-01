package classes.exam;

import java.util.ArrayList;

public class ExamRecord {

	private String examId;
	private String studentId;
	private double score;
	private int timeTaken;
	private ArrayList<Double> marks;

	public ExamRecord(String examId, String studentId, double score, int timeTaken, ArrayList<Double> marks) {
		this.examId = examId;
		this.studentId = studentId;
		this.score = score;
		this.timeTaken = timeTaken;
		this.marks = marks;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public ArrayList<Double> getMarks() {
		return marks;
	}

	public void setMarks(ArrayList<Double> marks) {
		this.marks = marks;
	}

	public String header() {
		return examId + "\n" + studentId + "\n" + score + "\n" + timeTaken + "\n";
	}

}
