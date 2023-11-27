package classes.exam;

import java.util.ArrayList;

public class ExamRecord {

	private String examId;
	private String studentId;
	private int score;
	private int timeTaken;
	private ArrayList<Integer> marks;

	public ExamRecord(String examId, String studentId, int score, int timeTaken, ArrayList<Integer> marks) {
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public ArrayList<Integer> getMarks() {
		return marks;
	}

	public void setMarks(ArrayList<Integer> marks) {
		this.marks = marks;
	}

	public String examRecordHeader() {
		return
				examId    + "\n" +
				studentId + "\n" +
				score     + "\n" +
				timeTaken + "\n";
	}

	@Override
	public String toString() {
		return "ExamRecord [examId=" + examId + ", studentId=" + studentId + ", score=" + score + ", timeTaken="
				+ timeTaken + ", marks=" + marks + "]";
	}

}
