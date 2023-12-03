package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.question.Question;
import classes.question.QuestionBank;
import classes.util.CustomList;
import classes.util.FileHandling;

public class QuestionRepository {

	private CustomList quesbank;
	private String filepath;

	public QuestionRepository(String filepath) {
		this.quesbank = new QuestionBank();
		this.filepath = filepath;
		if (!FileHandling.createFile(filepath) || !loadList()) {
			this.quesbank = null;
			this.filepath = null;
		}
	}

	public boolean readFile() {
		try {
			File repofile = new File(this.filepath);
			if (repofile.canRead()) {
				try (Scanner scanner = new Scanner(repofile)) {
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
				}
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean writeFile() {
		try {
			FileWriter writer = new FileWriter(this.filepath);
			for (Object ques : this.quesbank.getArr()) {
				writer.write(((Question) ques).getChapter() + " " + ((Question) ques).getDifficulty() + " "
						+ ((Question) ques).getCorrectAnswer() + "\n" + ((Question) ques).getContent() + "\n"
						+ ((Question) ques).getAnswer()[0] + "\n" + ((Question) ques).getAnswer()[1] + "\n"
						+ ((Question) ques).getAnswer()[2] + "\n" + ((Question) ques).getAnswer()[3] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean loadList() {
		return readFile();
	}

	public boolean saveList() {
		return writeFile();
	}

	public boolean addQuestion(Question ques) {
		if (quesbank.isObjectAdded(ques))
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
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getContent().contains(questionContent))
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Integer> indexSearchQuestionByChapter(int chapter) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getChapter() == chapter)
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Integer> indexSearchQuestionByDiffi(int difficulty) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getDifficulty() == difficulty)
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}

	public ArrayList<Question> searchQuestionByContent(String questionContent) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getContent().contains(questionContent))
				arrOfQuestion.add((Question) ques);
		return arrOfQuestion;
	}

	public ArrayList<Question> searchQuestionByChapter(int chapter) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getChapter() == chapter)
				arrOfQuestion.add((Question) ques);
		return arrOfQuestion;
	}

	public ArrayList<Question> searchQuestionByDiffi(int difficulty) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Object ques : this.quesbank.getArr())
			if (((Question) ques).getDifficulty() == difficulty)
				arrOfQuestion.add((Question) ques);
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
		return (QuestionBank) quesbank;
	}

	public void setQuesbank(QuestionBank quesbank) {
		this.quesbank = quesbank;
	}
}
