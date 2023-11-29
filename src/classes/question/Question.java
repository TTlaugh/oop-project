package classes.question;

import java.util.Arrays;

public class Question {

	private int chapter;
	private int difficulty;
	private String content;
	private String answer[];
	private int correctAnswer;
	
	public Question() {
		this.chapter = 0;
		this.difficulty = 0;
		this.content = null;
		this.answer = new String[4];
		this.correctAnswer = 0;
	}
	public Question(int chapter, int difficulty, String content, String[] answer, int correctAnswer) {
		this.chapter = chapter;
		this.difficulty = difficulty;
		this.content = content;
		this.answer = answer;
		this.correctAnswer = correctAnswer;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
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
	
	public String questionDetail() {
		return
				this.correctAnswer+"\n"+
				this.content      +"\n"+
				this.answer[0]    +"\n"+
				this.answer[1]    +"\n"+
				this.answer[2]    +"\n"+
				this.answer[3]    +"\n"
		;
	}

	@Override
	public String toString() {
		return "Question [chapter=" + chapter + ", difficulty=" + difficulty + ", content=" + content + ", answer="
				+ Arrays.toString(answer) + ", correctAnswer=" + correctAnswer + "]";
	}

}
