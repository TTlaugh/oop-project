package classes.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.util.CheckInput;
import classes.util.Date;

public class Session {

	public static boolean checkDate(Date examDate) {
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		return currentDate.equalsIgnoreCase(examDate.toString());
	}

	public static boolean run(ExamRepository examSource, ExamRecordRepository resultDestination, String examName,
			String studentId) {
		try {
			String examId;
			double score = 0;
			int timeLimit = 0, timeTaken = 0, numOfQuestions = 1;
			ArrayList<Integer> correctAnswer = new ArrayList<Integer>();
			ArrayList<Double> marks = new ArrayList<Double>();

			final File examFile = new File(examSource.getPath() + examName);
			Scanner scFile = new Scanner(examFile);
			System.out.println("Exam ID: " + (examId = scFile.nextLine()));
			System.out.println(scFile.nextLine());
			System.out.println("Subject: " + scFile.nextLine());
			System.out.println("Date: " + scFile.nextLine());
			System.out.println("Time: " + (timeLimit = Integer.parseInt(scFile.nextLine())));
			System.out.println(scFile.nextLine());
			System.out.println("=( Question List )=====================================");
			while (scFile.hasNextLine()) {
				correctAnswer.add(Integer.parseInt(scFile.nextLine()));
				System.out.println("Question " + (numOfQuestions++) + ":" + scFile.nextLine());
				System.out.println("[1]. " + scFile.nextLine());
				System.out.println("[2]. " + scFile.nextLine());
				System.out.println("[3]. " + scFile.nextLine());
				System.out.println("[4]. " + scFile.nextLine());
			}
			System.out.println("=( END )=====================================");

			final double scorePerQuestion = 10.0 / (double) numOfQuestions;
			Scanner scStdin = new Scanner(System.in);

			long startTime = System.nanoTime();

			do {
				score = 0;
				marks.clear();
				System.out.println(
						"STUDENT ANSWERS\n(Please enter answers in the correct question order)\n Type here ");

				for (int i = 0; i < numOfQuestions; i++) {
					String studentChoice = CheckInput.toStrNumberic(scStdin.next());
					if (correctAnswer.get(i).equals(studentChoice == null ? 0 : Integer.parseInt(studentChoice))) {
						marks.add(scorePerQuestion);
						score += scorePerQuestion;
					} else
						marks.add(0.0);
				}
				scStdin.nextLine();

				System.out.print("Do you want to Submit? [Y/n] ");
			} while (!CheckInput.toYesNo(scStdin.nextLine()));

			long endTime = System.nanoTime();
			timeTaken = (int) ((endTime - startTime) / 1000000000);

			if (timeTaken > timeLimit) {
				System.out.println("The time for submitting your exam has expired.\nYour exam will not be scored.");
				score = 0;
				marks = null;
				marks = new ArrayList<Double>(Collections.nCopies(numOfQuestions, 0.0));
			}

			resultDestination.addExamRecord(new ExamRecord(examId, studentId, score, timeTaken, marks), examName);

			scStdin.close();
			scFile.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
}
