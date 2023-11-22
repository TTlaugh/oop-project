package classes.question;

import java.util.ArrayList;

public class QuestionCountDetail {
	
	private int chapter;
	private ArrayList<Integer> difficultyCountDetail;

	public QuestionCountDetail(int chapter, ArrayList<Integer> difficultyCountDetail) {
		this.chapter = chapter;
		this.difficultyCountDetail = difficultyCountDetail;
	}

	public QuestionCountDetail() {
		this.chapter = 0;
		this.difficultyCountDetail = new ArrayList<Integer>();
	}

	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public ArrayList<Integer> getDifficultyCountDetail() {
		return difficultyCountDetail;
	}
	public void setDifficultyCountDetail(ArrayList<Integer> difficultyCountDetail) {
		this.difficultyCountDetail = difficultyCountDetail;
	}

}
