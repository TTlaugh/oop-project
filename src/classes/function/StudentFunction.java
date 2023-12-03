package classes.function;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Flow.Processor;
import java.io.File;
import classes.exam.*;
import classes.question.Question;
import classes.question.QuestionCountDetail;
import classes.question.QuestionSet;
import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.repository.QuestionRepository;
import classes.subject.Subject;
import classes.util.CheckInput;
import classes.util.Constant;
import classes.util.CustomDate;
import classes.util.FileHandling;
import classes.util.Menu;
import classes.user.*;

public class StudentFunction {

    public static void doExam(Scanner sc, Student Student) {
        System.out.println("Enter the subject's ID");

        Subject subject = new Subject(sc.nextLine());

        CustomDate date = new CustomDate();

        String newDate;
        do {
            System.out.println("nhap ngay thi:");
            newDate = CheckInput.toStrNumberic(sc.nextLine(), 1, 31);
            if (newDate == null)
                System.out.println("Please re-enter");
        } while (newDate == null);

        String newMonth;
        do {
            System.out.println("nhap thang:");
            newMonth = CheckInput.toStrNumberic(sc.nextLine(), 1, 12);
            if (newMonth == null)
                System.out.println("Please re-enter");
        } while (newMonth == null);

        String newYear;
        do {
            System.out.println("nhap nam:");
            newYear = CheckInput.toStrNumberic(sc.nextLine());
            if (newYear == null)
                System.out.println("Please re-enter");
        } while (newYear == null);

        date.setDay(newDate);
        date.setMonth(newMonth);
        date.setYear(newYear);
        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, Student.getClazz(),
                date);

        ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
                Student.getClazz(), date);

        File exam = new File(examRepository.getPath() + "exam" + Student.getOrnum());
        File examRecord = new File(examRecordRepository.getPath() + "exam" + Student.getOrnum());

        if (exam.exists()) {
            if (examRecord.exists()) {
                System.out.println("You have done all the exam");
            } else if (!Session.checkDate(date)) {
                System.out.println("Not the time for doing exam");
            } else {
                Session.run(sc, examRepository, examRecordRepository, "exam" + Student.getOrnum(),
                        Student.getUsername());
            }
        } else {
            System.out.println("You don't have any exams");
        }

    }

    public static void DisplayResult(Scanner sc, Student Student) {
        System.out.println("Enter the subject's ID");
        Subject subject = new Subject(sc.nextLine());

        String newDate, newMonth, newYear;

        CustomDate date = new CustomDate();
        do {

            System.out.println("Enter exam's day:");
            newDate = sc.nextLine();
            System.out.println("Enter exam's month:");
            newMonth = sc.nextLine();
            System.out.println("Enter exam's year:");
            newYear = sc.nextLine();

        } while (CheckInput.toDateFormat(newDate + "-" + newMonth + "-" + newYear) == null);

        date.setDay(newDate);
        date.setMonth(newMonth);
        date.setYear(newYear);

        ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
                Student.getClazz(), date);
        examRecordRepository.displayExamRecord("exam" + Student.getOrnum());

    }

}