package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.question.Question;
import classes.question.QuestionBank;

public class QuestionRepository {

	private QuestionBank quesbank;
	private String filepath;
	private File repofile;

	public QuestionRepository(String filepath) {
		this.quesbank = new QuestionBank(filepath);
		this.filepath = filepath;
		this.repofile = new File(this.filepath);
		if (!createfile() || !loadList()) {
			this.quesbank = null;
			this.filepath = null;
			this.repofile = null;
		}
	}

	private boolean createfile() {
		try {
			this.repofile = new File(this.filepath);
			if (!this.repofile.exists())
				this.repofile.createNewFile();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	private boolean readfile() {
		try {
			this.repofile = new File(this.filepath);
			if (this.repofile.canRead()) {
				Scanner scanner = new Scanner(this.repofile);
				while (scanner.hasNext()) {

					int chapter = scanner.nextInt();
					int difficulty = scanner.nextInt();
					int correctAnswer = scanner.nextInt();
					scanner.nextLine();
					String content = scanner.nextLine();
					String answer[] = new String[4];
					for (int i = 0; i < answer.length; i++)
						answer[i] = scanner.nextLine();

					this.quesbank.add(new Question(chapter, difficulty, content, answer, correctAnswer));
				}
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	private boolean writefile() {
		try {
			FileWriter writer = new FileWriter(this.filepath);
			for (Question ques : this.quesbank.getQuesList()) {
				writer.write(ques.getChapter() + " " + ques.getDifficulty() + " " + ques.getCorrectAnswer() + "\n"
						+ ques.getContent() + "\n" + ques.getAnswer()[0] + "\n" + ques.getAnswer()[1] + "\n"
						+ ques.getAnswer()[2] + "\n" + ques.getAnswer()[3] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean loadList() {
		return readfile();
	}

	public boolean saveList() {
		return writefile();
	}

	public boolean addQuestion(Question ques) {
		if (quesbank.isQuestionAdded(ques))
			return false;
		this.quesbank.add(ques);
		return saveList();
	}

	public boolean removeQuestion(int index) {
		quesbank.remove(index);
		return saveList();
	}

	public boolean modifyQuestion(int index, Question acc) {
		quesbank.set(index, acc);
		return saveList();
	}

	public ArrayList<Integer> indexSearchQuestionByContent(String questionContent) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getContent().contains(questionContent))
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Integer> indexSearchQuestionByChapter(int chapter) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getChapter() == chapter)
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Integer> indexSearchQuestionByDiffi(int difficulty) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getDifficulty() == difficulty)
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Question> searchQuestionByContent(String questionContent) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getContent().contains(questionContent))
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}

	public ArrayList<Question> searchQuestionByChapter(int chapter) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getChapter() == chapter)
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}

	public ArrayList<Question> searchQuestionByDiffi(int difficulty) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getDifficulty() == difficulty)
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}

	public static ArrayList<Question> searchQuestionByDiffi(ArrayList<Question> quesList, int difficulty) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : quesList)
			if (ques.getDifficulty() == difficulty)
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}

	public QuestionBank getQuesbank() {
		return quesbank;
	}

	public void setQuesbank(QuestionBank quesbank) {
		this.quesbank = quesbank;
	}
}
