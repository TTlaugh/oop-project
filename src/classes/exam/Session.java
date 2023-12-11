package classes.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.util.CheckInput;
import classes.util.CustomDate;

public class Session {

	public static boolean checkDate(CustomDate examDate) {
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		return currentDate.equalsIgnoreCase(examDate.toString());
	}

	private static void printResult(double score, int timeTaken) {
		String text = ""
				+ "\t╔════════════════════════╗\n"
				+ "\t      SCORE: %.2f         \n"
				+ "\t      Time taken: %d      \n"
				+ "\t╚════════════════════════╝\n";
		System.out.printf(text, score, timeTaken);
	}

	public static boolean run(Scanner userInput, ExamRepository examSource, ExamRecordRepository resultDestination,
			String examName, String studentId) {
		try {
			String examId = null;
			double score = 0;
			int timeLimit = 0, timeTaken = 0, numOfQuestions = 0;
			ArrayList<Integer> correctAnswer = new ArrayList<Integer>();
			ArrayList<Double> marks = new ArrayList<Double>();

			final File examFile = new File(examSource.getPath() + examName);
			try (Scanner scFile = new Scanner(examFile)) {
				System.out.println("Exam ID: " + (examId = scFile.nextLine()));
				System.out.println(scFile.nextLine());
				System.out.println("Subject: " + scFile.nextLine());
				System.out.println("Date: " + scFile.nextLine());
				System.out.println("Time: " + (timeLimit = Integer.parseInt(scFile.nextLine())));
				System.out.println(scFile.nextLine());
				System.out.println("=( Question List )=====================================");
				while (scFile.hasNextLine()) {
					correctAnswer.add(Integer.parseInt(scFile.nextLine()));
					System.out.println("Question " + (1 + (numOfQuestions++)) + ":" + scFile.nextLine());
					System.out.println("[1]. " + scFile.nextLine());
					System.out.println("[2]. " + scFile.nextLine());
					System.out.println("[3]. " + scFile.nextLine());
					System.out.println("[4]. " + scFile.nextLine());
				}
			}
			System.out.println("=( END )=====================================");

			final double scorePerQuestion = 10.0 / (double) numOfQuestions;

			long startTime = System.nanoTime();

			do {
				score = 0;
				marks.clear();
				System.out.println(
						"STUDENT ANSWERS\n(Please enter your answers in the correct order, with spaces between answers)\n Type here ");

				for (int i = 0; i < numOfQuestions; i++) {
					String studentChoice = CheckInput.toStrInteger(userInput.next());
					if (correctAnswer.get(i).equals(studentChoice == null ? 0 : Integer.parseInt(studentChoice))) {
						marks.add(scorePerQuestion);
						score += scorePerQuestion;
					} else
						marks.add(0.0);
				}
				userInput.nextLine();

				System.out.print("Do you want to Submit? [Y/n] ");
			} while (!CheckInput.toYesNo(userInput.nextLine()));

			long endTime = System.nanoTime();
			timeTaken = (int) ((endTime - startTime) / 1000000000);
			timeTaken /= 60;

			if (timeTaken > timeLimit) {
				System.out.println("The time for submitting your exam has expired.\nYour exam will not be scored.");
				score = 0;
				marks = null;
				marks = new ArrayList<Double>(Collections.nCopies(numOfQuestions, 0.0));
			}

			printResult(score, timeTaken);

			resultDestination.addExamRecord(new ExamRecord(examId, studentId, score, timeTaken, marks), examName);

		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
}
