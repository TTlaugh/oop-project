package classes.repository;

import classes.question.*;

import java.io.*;
import java.util.*;

public class QuestionRepository {

	private QuestionBank quesbank;
	private String filepath;
	private File repofile;
	
	public QuestionRepository() {
		this.quesbank = new QuestionBank();
		this.filepath = null;
		this.repofile = null;
	}
	public QuestionRepository(String filepath) {
		this.quesbank = new QuestionBank(filepath);
		this.filepath = filepath;
		this.repofile = new File(this.filepath);
		if (!repofile.exists()) createfile();
		loadList();
	}

	private void createfile() {
	    try {
	    	this.repofile = new File(this.filepath);
	    	this.repofile.createNewFile();
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }	
	}
	private void readfile() {
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
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	private void writefile() {
		try {
			FileWriter writer = new FileWriter(this.filepath);
			for (Question ques : this.quesbank.getQuesList()) {
				writer.write(
					ques.getChapter()+" "+ ques.getDifficulty()+" "+ ques.getCorrectAnswer()+"\n"+
					ques.getContent()+"\n"+
					ques.getAnswer()[0]+"\n"+
					ques.getAnswer()[1]+"\n"+
					ques.getAnswer()[2]+"\n"
				);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void loadList() {
		readfile();
	}
	public void saveList() {
		writefile();
	}

	public boolean addQuestion(Question ques) {
		if (quesbank.isQuestionAdded(ques)) return false;
		this.quesbank.add(ques);
		saveList();
		return true;
	}
	public boolean removeQuestion(int index) {
		quesbank.remove(index);
		saveList();
		return true;
	}
	public boolean modifyQuestion(int index, Question acc) {
		quesbank.set(index, acc);
		saveList();
		return true;
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
			if (ques.getChapter()==chapter)
				arrOfIndex.add(this.quesbank.findIndex(ques));
		return arrOfIndex;
	}
	public ArrayList<Integer> indexSearchQuestionByDiffi(int difficulty) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getDifficulty()==difficulty)
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
			if (ques.getChapter()==chapter)
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}
	public ArrayList<Question> searchQuestionByDiffi(int difficulty) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : this.quesbank.getQuesList())
			if (ques.getDifficulty()==difficulty)
				arrOfQuestion.add(ques);
		return arrOfQuestion;
	}

	public static ArrayList<Question> searchQuestionByDiffi(ArrayList<Question> quesList, int difficulty) {
		ArrayList<Question> arrOfQuestion = new ArrayList<Question>();
		for (Question ques : quesList)
			if (ques.getDifficulty()==difficulty)
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
