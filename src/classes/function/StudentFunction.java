package classes.function;

import java.io.File;
import java.util.Scanner;

import classes.exam.Session;
import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.subject.Subject;
import classes.user.Student;
import classes.util.CheckInput;
import classes.util.Constant;
import classes.util.CustomDate;
import classes.util.FileHandling;

public class StudentFunction {

	public static void takeExam(Scanner sc, Student student) {
		FileHandling subjectFile = new FileHandling(Constant.dataPath.SubjectList_File);
		System.out.println("Subject List:");
		subjectFile.displayContent();
		Subject subject=null;
		do {
			System.out.print("Enter the subject's ID: ");
			subject = new Subject(sc.nextLine());
			if (subject.getName()==null)
				System.out.println("Invalid subject's ID");
		} while (subject.getName()==null);

		String inputDate;
		do {
			System.out.println("Enter exam's day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(sc.nextLine());
			if (inputDate==null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);

		CustomDate date = new CustomDate(inputDate);
		
		ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, student.getClazz(),
				date);

		ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
				student.getClazz(), date);

		File exam = new File(examRepository.getPath() + "exam" + student.getOrnum());
		File examRecord = new File(examRecordRepository.getPath() + "exam" + student.getOrnum());

		if (exam.exists()) {
			if (examRecord.exists()) {
				System.out.println("You have done all the exam");
			} else if (!Session.checkDate(date)) {
				System.out.println("Not the time for doing exam");
			} else {
				Session.run(sc, examRepository, examRecordRepository, "exam" + student.getOrnum(),
						student.getUsername());
			}
		} else {
			System.out.println("You don't have any exams");
		}

	}

	public static void viewResult(Scanner sc, Student Student) {
		FileHandling subjectFile = new FileHandling(Constant.dataPath.SubjectList_File);
		System.out.println("Subject List:");
		subjectFile.displayContent();
		Subject subject=null;
		do {
			System.out.print("Enter the subject's ID: ");
			subject = new Subject(sc.nextLine());
			if (subject.getName()==null)
				System.out.println("Invalid subject's ID");
		} while (subject.getName()==null);

		String inputDate;
		do {
			System.out.println("Enter exam's day (dd-mm-yyyy): ");
			inputDate = CheckInput.toDateFormat(sc.nextLine());
			if (inputDate==null)
				System.out.println("Invalid day-month-year");
		} while (inputDate == null);

		CustomDate date = new CustomDate(inputDate);

		ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
				Student.getClazz(), date);
		examRecordRepository.displayExamRecord("exam" + Student.getOrnum());

	}

}