package classes.question;

import java.util.Arrays;

public class Question {

	private String id;
	private int difficulty;
	private String content;
	private String answer[];
	private int correctAnswer;

	public Question(String id, int difficulty, String content, String[] answer, int correctAnswer) {
		super();
		this.id = id;
		this.difficulty = difficulty;
		this.content = content;
		this.answer = answer;
		this.correctAnswer = correctAnswer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", difficulty=" + difficulty + ", content=" + content + ", answer="
				+ Arrays.toString(answer) + ", correctAnswer=" + correctAnswer + "]";
	}
	
}
