package classes.function;

import java.util.ArrayList;
import java.util.Scanner;

import classes.exam.Exam;
import classes.question.Question;
import classes.question.QuestionCountDetail;
import classes.question.QuestionSet;
import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.repository.QuestionRepository;
import classes.subject.Subject;
import classes.user.Professor;
import classes.util.CheckInput;
import classes.util.Constant;
import classes.util.CustomDate;
import classes.util.FileHandling;
import classes.util.Menu;

public class ProfessorFunction {

	public static boolean questionManager(Scanner userInput, Professor professor, String choice) {
		QuestionRepository questionRepository = new QuestionRepository(
				Constant.dataPath.QuestionBanks_Dir + professor.getSubject().getId());
		switch (choice) {
		case "1":
			showQuestionBank(questionRepository);
			break;
		case "2":
			addQuestion(userInput, questionRepository);
			break;
		case "3":
			searchQuestion(userInput, questionRepository);
			break;
		default:
			break;
		}

		return true;
	}

	public static boolean examManager(Scanner userInput, Professor professor, String choice) {
		switch (choice) {
		case "1":
			createExam(userInput, professor.getSubject());
			break;
		case "2":
			previewExam(userInput, professor.getSubject());
			break;
		case "3":
			addExam(userInput, professor.getSubject());
			break;
		case "4":
			deleteExam(userInput, professor.getSubject());
			break;
		case "5":
			displayListResult(userInput, professor.getSubject());
			break;
		case "6":
			displayExamRecordContent(userInput, professor.getSubject());
			break;
		default:
			break;
		}
		return true;
	}

	public static void showQuestionBank(QuestionRepository questionRepository) {
		System.out.println("Question List:");
		// questionRepository.getQuesbank().setSubject(subject);
		for (Object ques : questionRepository.getQuesbank().getArr()) {
			System.out.println((Question) ques);
		}
	}

	public static void searchQuestion(Scanner userInput, QuestionRepository questionRepository) {
		ArrayList<Integer> arrOfIndex = new ArrayList<Integer>();
		switch (Menu.searchQuestion(userInput)) {
		case "1":
			System.out.println("Search here (content)");
			arrOfIndex = questionRepository.indexSearchQuestionByContent(userInput.nextLine());
			break;
		case "2":
			System.out.println("Search here (chapter)");
			String strChapter = CheckInput.toStrNumberic(userInput.nextLine());
			arrOfIndex = questionRepository
					.indexSearchQuestionByChapter(strChapter == null ? -1 : Integer.parseInt(strChapter));
			break;
		case "3":
			System.out.println("Search here (difficulty: 0 - 2)");
			String strDiff = CheckInput.toStrNumberic(userInput.nextLine(), 0, 2);
			arrOfIndex = questionRepository
					.indexSearchQuestionByDiffi(strDiff == null ? -1 : Integer.parseInt(strDiff));
			break;
		default:
			break;
		}
		if (arrOfIndex.isEmpty()) {
			System.out.println("Question not found.");
		} else {
			System.out.println("Founded questions:");
			for (int i = 0; i < arrOfIndex.size(); i++) {
				int index = arrOfIndex.get(i);
				System.out.println("[" + index + "] " + questionRepository.getQuesbank().getArr().get(index));
			}
			System.out.println();
			System.out.println("[1] Edit question");
			System.out.println("[2] Remove question");
			String choice = CheckInput.toStrNumberic(userInput.nextLine());
			choice = choice == null ? "" : choice;
			String strIndex = null;
			switch (choice) {
			case "1":
				System.out.print("Enter the index you want to edit: ");
				strIndex = CheckInput.toStrNumberic(userInput.nextLine());
				if (strIndex == null)
					break;
				modifyQuestionAtIndex(userInput, questionRepository, Integer.parseInt(strIndex));
				break;
			case "2":
				System.out.print("Enter the index you want to remove: ");
				strIndex = CheckInput.toStrNumberic(userInput.nextLine());
				if (strIndex == null)
					break;
				questionRepository.removeQuestion(0);
				break;
			default:
				break;
			}
		}
	}

	public static void addQuestion(Scanner sc, QuestionRepository questionRepository) {
		int chapter = 0;
		do {
			System.out.println("Enter chapter :");
			chapter = CheckInput.toIntNumeric(sc.nextLine());
		} while (chapter == -1);

		int difficulty;
		do {
			System.out.println("Choose difficulty [0] - [2]");
			difficulty = CheckInput.toIntNumeric(sc.nextLine(), 0, 2);
		} while (difficulty == -1);

		System.out.println("Enter content: ");
		String content = sc.nextLine();

		String[] answer = new String[4];
		for (int i = 0; i < answer.length; i++) {
			System.out.println("Enter answer " + (i + 1) + ": ");
			answer[i] = sc.nextLine();
		}

		int correctAnswer;
		do {
			System.out.println("Enter correct answer [1 - 4]: ");
			correctAnswer = CheckInput.toIntNumeric(sc.nextLine(), 1, 4);
		} while (correctAnswer == -1);

		Question newQuestion = new Question(chapter, difficulty, content, answer, correctAnswer);
		if (questionRepository.addQuestion(newQuestion))
			System.out.println("Question has been added.");
		else
			System.out.println("Fail to add the question");
	}

	public static void modifyQuestionAtIndex(Scanner userInput, QuestionRepository questionRepository, int index) {

		int newChapter;
		do {
			System.out.println("Enter new chapter: ");
			newChapter = CheckInput.toIntNumeric(userInput.nextLine());
		} while (newChapter == -1);

		int newDifficulty;
		do {
			System.out.println("Enter new difficulty: [0] - [2]");
			newDifficulty = CheckInput.toIntNumeric(userInput.nextLine(), 0, 2);
		} while (newDifficulty == -1);

		System.out.println("Enter new content:");
		String newContent = userInput.nextLine();

		String[] newAnswer = new String[4];
		for (int i = 0; i < newAnswer.length; i++) {
			System.out.println("Enter new answer " + (i + 1) + ":");
			newAnswer[i] = userInput.nextLine();
		}

		int newCorrectAnswer;
		do {
			System.out.println("Enter new correct answer [1 - 4]:]");
			newCorrectAnswer = CheckInput.toIntNumeric(userInput.nextLine(), 1, 4);
		} while (newCorrectAnswer == -1);

		// Modify the question
		Question modifiedQuestion = new Question(newChapter, newDifficulty, newContent, newAnswer, newCorrectAnswer);
		if (questionRepository.modifyQuestion(index, modifiedQuestion))
			System.out.println("Question modified.");
		else
			System.out.println("Fail to modify this question");
	}

	public static void createExam(Scanner userInput, Subject subject) {

		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);

		CustomDate date = new CustomDate(inputDate);

		ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);

		System.out.println("Exam name:");
		String examName = userInput.nextLine();

		System.out.println("Note:");
		String examNote = userInput.nextLine();

		int examTime;
		do {
			System.out.println("Duration (minutes):");
			examTime = CheckInput.toIntNumeric(userInput.nextLine());
		} while (examTime == -1);

		int totalQuestions;
		do {
			System.out.println("Enter the total number of questions:");
			totalQuestions = CheckInput.toIntNumeric(userInput.nextLine());
			if (totalQuestions == -1)
				System.out.println("Invalid value");
		} while (totalQuestions == -1);

		QuestionSet questionSet = null;
		do {
			ArrayList<QuestionCountDetail> questionCountDetails = new ArrayList<QuestionCountDetail>();
			QuestionCountDetail countDetail = null;
			int count = 0;
			while (count < totalQuestions) {
				System.out.print("Enter the chapter number:");
				int chapter = CheckInput.toIntNumeric(userInput.nextLine());
				ArrayList<Integer> difficultyCountDetail = new ArrayList<Integer>();

				int numOfType = 0;
				System.out.print("Number of easy: ");
				numOfType = CheckInput.toIntNumeric(userInput.nextLine());
				numOfType = numOfType == -1 ? 0 : numOfType;
				if (numOfType != 0)
					difficultyCountDetail.add(numOfType);
				count += (numOfType == -1 ? 0 : numOfType);
				System.out.print("Number of medium: ");
				numOfType = CheckInput.toIntNumeric(userInput.nextLine());
				numOfType = numOfType == -1 ? 0 : numOfType;
				if (numOfType != 0)
					difficultyCountDetail.add(numOfType);
				count += (numOfType == -1 ? 0 : numOfType);
				System.out.print("Number of hard: ");
				numOfType = CheckInput.toIntNumeric(userInput.nextLine());
				numOfType = numOfType == -1 ? 0 : numOfType;
				if (numOfType != 0)
					difficultyCountDetail.add(numOfType);
				count += (numOfType == -1 ? 0 : numOfType);

				countDetail = new QuestionCountDetail(chapter, difficultyCountDetail);

				questionCountDetails.add(countDetail);
			}

			questionSet = new QuestionSet(subject, totalQuestions, questionCountDetails);
			if (questionSet.getQuesSet() == null)
				System.out.println("The number of questions is incorrect,"
						+ "\npossibly due to entering more than the number of questions in the bank"
						+ "\nor entering an invalid value" + "\nPlease Enter Again");
		} while (questionSet.getQuesSet() == null);

		Exam exam = new Exam(" ", examName, subject, examNote, date, examTime, questionSet);

		int numOfExams;
		do {
			System.out.print("Enter num of exams: ");
			numOfExams = CheckInput.toIntNumeric(userInput.nextLine());
			if (numOfExams == -1)
				System.out.println("Please re-enter");
		} while (numOfExams == -1);
		int numOfExamId;
		do {
			System.out.print("Enter num of exam ID: ");
			numOfExamId = CheckInput.toIntNumeric(userInput.nextLine());
			if (numOfExamId == -1)
				System.out.println("Please re-enter");
		} while (numOfExamId == -1);

		if (examRepository.createExams(exam, numOfExams, numOfExamId)) {
			System.out.println("Exam added successfully.");
		} else {
			System.out.println("Failed to add exam.");
		}
	}

	public static void previewExam(Scanner userInput, Subject subject) {
		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		System.out.println("Available exam day:");
		FileHandling examday = new FileHandling(Constant.dataPath.Exams_Dir + subject.getId() + "/" + clazz + "/");
		if (!examday.listFileInDir()) {
			System.out.println("Nothing to show...\nDoes not have any exam created");
			return;
		}
		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);
		CustomDate date = new CustomDate(inputDate);

		ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);
		examRepository.listAllExamsCreated();
		System.out.println("Enter exam name:");
		String examname = userInput.nextLine();
		if (!examRepository.previewExam(examname)) {
			System.out.println("Wrong exam name!");
		}
	}

	public static void deleteExam(Scanner userInput, Subject subject) {
		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		System.out.println("Available exam day:");
		FileHandling examday = new FileHandling(Constant.dataPath.Exams_Dir + subject.getId() + "/" + clazz + "/");
		if (!examday.listFileInDir()) {
			System.out.println("Nothing to show...\nDoes not have any exam created");
			return;
		}
		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);
		CustomDate date = new CustomDate(inputDate);

		ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);
		System.out.println("All exam on the selected date:");
		examRepository.listAllExamsCreated();
		System.out.println("Enter exam name you want to delete:");
		String examname = userInput.nextLine();
		if (examRepository.removeExam(examname)) {
			System.out.println("Exam deleted!");
		} else {
			System.out.println("Wrong exam name!");
		}
	}

	public static void addExam(Scanner userInput, Subject subject) {
		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);
		CustomDate date = new CustomDate(inputDate);

		ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);

		System.out.println("Enter ID exam:");
		String examId = userInput.nextLine();

		System.out.println("Exam name:");
		String examName = userInput.nextLine();

		System.out.println("Note:");
		String examNote = userInput.nextLine();

		int examTime;
		do {
			System.out.println("Duration (minutes):");
			examTime = CheckInput.toIntNumeric(userInput.nextLine());
			if (examTime == -1)
				System.out.println("Please re-enter");
		} while (examTime == -1);

		int totalQuestions;
		do {
			System.out.println("Enter the total number of questions:");
			totalQuestions = CheckInput.toIntNumeric(userInput.nextLine());
			if (totalQuestions == -1)
				System.out.println("Please re-enter");
		} while (totalQuestions == -1);

		ArrayList<QuestionCountDetail> questionCountDetails = new ArrayList<QuestionCountDetail>();

		QuestionCountDetail countDetail = null;
		int count = 0;
		while (count < totalQuestions) {
			System.out.print("Enter the chapter number:");
			int chapter = CheckInput.toIntNumeric(userInput.nextLine());
			ArrayList<Integer> difficultyCountDetail = new ArrayList<Integer>();

			int numOfType = 0;
			System.out.print("Number of easy: ");
			numOfType = CheckInput.toIntNumeric(userInput.nextLine());
			numOfType = numOfType == -1 ? 0 : numOfType;
			if (numOfType != 0)
				difficultyCountDetail.add(numOfType);
			count += (numOfType == -1 ? 0 : numOfType);
			System.out.print("Number of medium: ");
			numOfType = CheckInput.toIntNumeric(userInput.nextLine());
			numOfType = numOfType == -1 ? 0 : numOfType;
			if (numOfType != 0)
				difficultyCountDetail.add(numOfType);
			count += (numOfType == -1 ? 0 : numOfType);
			System.out.print("Number of hard: ");
			numOfType = CheckInput.toIntNumeric(userInput.nextLine());
			numOfType = numOfType == -1 ? 0 : numOfType;
			if (numOfType != 0)
				difficultyCountDetail.add(numOfType);
			count += (numOfType == -1 ? 0 : numOfType);

			countDetail = new QuestionCountDetail(chapter, difficultyCountDetail);

			questionCountDetails.add(countDetail);

		}

		QuestionSet questionSet = new QuestionSet(subject, totalQuestions, questionCountDetails);

		Exam exam = new Exam(examId, examName, subject, examNote, date, examTime, questionSet);
		userInput.nextLine();

		System.out.println("Enter exam destination file name:");
		String examFileName = userInput.nextLine();

		if (examRepository.addExam(exam, examFileName)) {
			System.out.println("Exam added successfully.");
		} else {
			System.out.println("Failed to add exam.");
		}
	}

	public static void displayListResult(Scanner userInput, Subject subject) {

		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		System.out.println("Available exam day:");
		FileHandling examday = new FileHandling(
				Constant.dataPath.ExamRecords_Dir + subject.getId() + "/" + clazz + "/");
		if (!examday.listFileInDir()) {
			System.out.println("Nothing to show...\nDoes not have any exam record created");
			return;
		}
		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);
		CustomDate date = new CustomDate(inputDate);

		ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
				clazz, date);
		System.out.println("Exam result transcript:");
		examRecordRepository.displaySummaryResults();
	}

	public static void displayExamRecordContent(Scanner userInput, Subject subject) {

		String clazz = null;
		do {
			System.out.println("Enter class Name (number:[1-9]):");
			clazz = CheckInput.toStrNumberic(userInput.nextLine(), 1, 9);
		} while (clazz == null);

		System.out.println("Available exam day:");
		FileHandling examday = new FileHandling(
				Constant.dataPath.ExamRecords_Dir + subject.getId() + "/" + clazz + "/");
		if (!examday.listFileInDir()) {
			System.out.println("Nothing to show...\nDoes not have any exam record created");
			return;
		}
		String inputDate;
		do {
			System.out.println("Enter exam day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(userInput.nextLine());
			if (inputDate == null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);
		CustomDate date = new CustomDate(inputDate);

		ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
				clazz, date);

		System.out.println("Exams List");
		examRecordRepository.listAllExamRecordsCreated();
		System.out.println("Enter the Name of the exam");
		String examname = userInput.nextLine();
		if (!examRecordRepository.displayExamRecord(examname)) {
			System.out.println("Failed to load this exam.");
		}
	}

}
