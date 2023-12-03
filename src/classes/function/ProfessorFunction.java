package classes.function;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Flow.Processor;

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

public class ProfessorFunction {

    public static void ShowQuesBank(Subject subject) {
        QuestionRepository questionRepository = new QuestionRepository(
                Constant.dataPath.QuestionBanks_Dir + subject.getId());
        System.out.println("Question List:");
        // questionRepository.getQuesbank().setSubject(subject);
        for (Object ques : questionRepository.getQuesbank().getArr()) {
            System.out.println(ques);
        }
    }

    public static void addQuestion(Subject subject, Scanner sc) {
        QuestionRepository questionRepository = new QuestionRepository(
                Constant.dataPath.QuestionBanks_Dir + subject.getId());

        System.out.println("Choose chapter :");
        int chapter = CheckInput.toIntNumeric(sc.nextLine());
        while (chapter == -1) {
            System.out.println("Please re-enter");
            System.out.println("Choose chapter :");
            chapter = CheckInput.toIntNumeric(sc.nextLine());
        }

        System.out.println("Enter content: ");
        String content = sc.nextLine();

        int difficulty;
        do {
            System.out.println("Choose difficulty [0] - [2]");
            difficulty = CheckInput.toIntNumeric(sc.nextLine(), 0, 2);
            if (difficulty == -1)
                System.out.println("Please re-enter");
        } while (difficulty == -1);

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
        questionRepository.addQuestion(newQuestion);
        System.out.println("Question has been added.");
    }

    private static void removeQuestion(Subject subject, Scanner sc) {
        QuestionRepository questionRepository = new QuestionRepository(
                Constant.dataPath.QuestionBanks_Dir + subject.getId());

        ArrayList<Integer> indices = new ArrayList<>();

        switch (Menu.removeQuestion(sc)) {
            case "1":
                System.out.println("Enter content of question:");
                indices = questionRepository.indexSearchQuestionByContent(sc.nextLine());
                break;
            case "2":
                System.out.println("Enter chapter of question:");
                indices = questionRepository.indexSearchQuestionByChapter(sc.nextInt());
                break;
            case "3":
                System.out.println("Enter difficulty of question:");
                indices = questionRepository.indexSearchQuestionByDiffi(sc.nextInt());
                break;
            default:
                System.out.println("Incorrect input");
                return;
        }

        if (indices.isEmpty()) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Founded questions:");

        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            System.out.println("[" + (index + 1) + "] " + questionRepository.getQuesbank().getArr().get(index));
        }
        int choiceToRemove;
        do {
            System.out.println("Choose question to remove:");
            choiceToRemove = CheckInput.toIntNumeric(sc.nextLine());
            if ((int) choiceToRemove < 1 && choiceToRemove > indices.size() && choiceToRemove == -1) {
                System.out.println("Wrong input");
                System.out.println("Please re-enter");
            }
        } while ((int) choiceToRemove < 1 && choiceToRemove > indices.size() && choiceToRemove == -1);
        int indexToRemove = indices.get(choiceToRemove - 1);
        questionRepository.removeQuestion(indexToRemove);
        System.out.println("Question deleted.");
    }

    private static void modifyQuestion(Scanner sc, Subject subject) {
        QuestionRepository questionRepository = new QuestionRepository(
                Constant.dataPath.QuestionBanks_Dir + subject.getId());

        ArrayList<Integer> indices = new ArrayList<>();

        switch (Menu.modifyQuestion(sc)) {
            case "1":
                System.out.println("Enter content of question to modify:");
                indices = questionRepository.indexSearchQuestionByContent(sc.nextLine());
                break;
            case "2":
                System.out.println("Enter chapter of question to modify:");
                indices = questionRepository.indexSearchQuestionByChapter(sc.nextInt());
                break;
            case "3":
                System.out.println("Enter difficulty of question to modify:");
                indices = questionRepository.indexSearchQuestionByDiffi(sc.nextInt());
                break;
            default:
                System.out.println("Incorrect input");
                return;
        }

        if (indices.isEmpty()) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Founded questions:");
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            System.out.println("[" + (index + 1) + "] " + questionRepository.getQuesbank().getArr().get(index));
        }
        int choiceToModify;
        do {
            System.out.println("Choose question to modify:");
            choiceToModify = CheckInput.toIntNumeric(sc.nextLine());

            if ((int) choiceToModify < 1 && choiceToModify > indices.size() && choiceToModify == -1) {
                System.out.println("Wrong input");
                System.out.println("Please re-enter");
            }
        } while ((int) choiceToModify < 1 && choiceToModify > indices.size() && choiceToModify == -1);
        int indexToModify = indices.get(choiceToModify - 1);
        modifyQuestionAtIndex(sc, subject, questionRepository, indexToModify);
    }

    private static void modifyQuestionAtIndex(Scanner sc, Subject subject, QuestionRepository questionRepository,
            int indexToModify) {

        Object questionToModify = questionRepository.getQuesbank().getArr().get(indexToModify);

        System.out.println("Enter new content:");
        String newContent = sc.nextLine();

        int newChapter;
        do {
            System.out.println("Enter new difficulty: :");
            newChapter = CheckInput.toIntNumeric(sc.nextLine());
            if (newChapter == -1)
                System.out.println("Please re-enter");
        } while (newChapter == -1);

        int newDifficulty;
        do {
            System.out.println("Enter new difficulty: [0] - [2]");
            newDifficulty = CheckInput.toIntNumeric(sc.nextLine(), 0, 2);
            if (newDifficulty == -1)
                System.out.println("Please re-enter");
        } while (newDifficulty == -1);

        String[] newAnswer = new String[4];
        for (int i = 0; i < newAnswer.length; i++) {
            System.out.println("Enter new answer " + (i + 1) + ":");
            newAnswer[i] = sc.nextLine();
        }

        int newCorrectAnswer;
        do {
            System.out.println("Enter new correct answer [1 - 4]:]");
            newCorrectAnswer = CheckInput.toIntNumeric(sc.nextLine(), 1, 4);
            if (newCorrectAnswer == -1)
                System.out.println("Please re-enter");
        } while (newCorrectAnswer == -1);

        // Modify the question
        Question modifiedQuestion = new Question(newChapter, newDifficulty, newContent, newAnswer, newCorrectAnswer);
        questionRepository.modifyQuestion(indexToModify, modifiedQuestion);

        System.out.println("Question modified.");
    }

    public static void createExam(Scanner sc, Subject subject) {

        System.out.println("Enter class's Name:");
        String clazz = sc.nextLine();
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

        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);

        System.out.println("Enter exam's name:");
        String examName = CheckInput.toStrNumberic(sc.nextLine());

        while (examName == null) {
            System.out.println("Please re-enter");
            System.out.println("Enter exam's name:");
            examName = CheckInput.toStrNumberic(sc.nextLine());
        }
        System.out.println("Enter exam's note:");
        String examNote = sc.nextLine();

        int examTime;
        do {
            System.out.println("nhap exam time:");
            examTime = CheckInput.toIntNumeric(sc.nextLine());
            if (examTime == -1)
                System.out.println("Please re-enter");
        } while (examTime == -1);

        sc.nextLine();

        int totalQuestions;
        do {
            System.out.println("Enter the total number of questions (quesCount):");
            totalQuestions = CheckInput.toIntNumeric(sc.nextLine());
            if (totalQuestions == -1)
                System.out.println("Please re-enter");
        } while (totalQuestions == -1);

        ArrayList<QuestionCountDetail> questionCountDetails = new ArrayList<QuestionCountDetail>();

        QuestionCountDetail countDetail = null;
        int dem = 0;
        while (dem < totalQuestions) {
            System.out.print("Enter the chapter number:");
            int chapter = sc.nextInt();
            ArrayList<Integer> difficultyCountDetail = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter the number of questions for difficulty level " + (i) + ": ");
                int difficultyCount = sc.nextInt();
                difficultyCountDetail.add(difficultyCount);
                dem = dem + difficultyCount;
            }

            countDetail = new QuestionCountDetail(chapter, difficultyCountDetail);

            System.out.println(countDetail.getDifficultyCountDetail());

            questionCountDetails.add(countDetail);

        }

        QuestionSet questionSet = new QuestionSet(subject, totalQuestions, questionCountDetails);

        Exam exam = new Exam(" ", examName, subject, examNote, date, examTime, questionSet);

        int numOfExams;
        do {
            System.out.println("Enter num of exams:");
            numOfExams = CheckInput.toIntNumeric(sc.nextLine());
            if (numOfExams == -1)
                System.out.println("Please re-enter");
        } while (numOfExams == -1);
        sc.nextLine();
        int numOfExamId;
        do {
            System.out.println("Enter num of exam ID");
            numOfExamId = CheckInput.toIntNumeric(sc.nextLine());
            if (numOfExamId == -1)
                System.out.println("Please re-enter");
        } while (numOfExamId == -1);
        sc.nextLine();

        if (examRepository.createExams(exam, numOfExams, numOfExamId)) {
            System.out.println("Exam added successfully.");
        } else {
            System.out.println("Failed to add exam.");
        }
    }

    public static void DisplayExam(Subject subject, Scanner sc) {
        String clazz;
        do {
            System.out.println("Nhap ten class:");
            clazz = CheckInput.toStrNumberic(sc.nextLine(), 0, 100);
            if (clazz == null)
                System.out.println("Please re-enter");
        } while (clazz == null);

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

        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);
        examRepository.listAllExamsCreated();
        System.out.println("Enter exam's name:");
        String examname = sc.nextLine();
        System.out.println(examRepository.previewExam(examname));
    }

    public static void deleteExam(Subject subject, Scanner sc) {
        System.out.println("Enter class's Name:");
        String clazz = sc.nextLine();
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

        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);
        System.out.println("All exam on the selected date");
        examRepository.listAllExamsCreated();
        System.out.println("Enter exam's name you want to delete");
        String examname = sc.nextLine();
        if (examRepository.removeExam(examname)) {
            System.out.println("Exam deleted!");
        } else {
            System.out.println("Wrong exam's name!");
        }
    }

    public static void addExam(Subject subject, Scanner sc) {
        System.out.println("Enter class's Name:");
        String clazz = sc.nextLine();
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

        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, clazz, date);
        System.out.println("Enter exam's id:");
        String examId = sc.nextLine();
        System.out.println("Enter exam's name:");
        String examName = sc.nextLine();
        System.out.println("Enter your note:");
        String examNote = sc.nextLine();
        int examTime;
        do {
            System.out.println("nhap exam time:");
            examTime = CheckInput.toIntNumeric(sc.nextLine());
            if (examTime == -1)
                System.out.println("Please re-enter");
        } while (examTime == -1);

        int totalQuestions;

        do {
            System.out.println("Enter the total number of questions (quesCount):");
            totalQuestions = CheckInput.toIntNumeric(sc.nextLine());
            if (totalQuestions == -1)
                System.out.println("Please re-enter");
        } while (totalQuestions == -1);

        ArrayList<QuestionCountDetail> questionCountDetails = new ArrayList<QuestionCountDetail>();

        QuestionCountDetail countDetail = null;
        int dem = 0;
        while (dem < totalQuestions) {
            System.out.print("Enter the chapter number:");
            int chapter = sc.nextInt();
            ArrayList<Integer> difficultyCountDetail = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter the number of questions for difficulty level " + (i) + ": ");
                int difficultyCount = sc.nextInt();
                difficultyCountDetail.add(difficultyCount);
                dem = dem + difficultyCount;
            }

            countDetail = new QuestionCountDetail(chapter, difficultyCountDetail);

            System.out.println(countDetail.getDifficultyCountDetail());

            questionCountDetails.add(countDetail);

        }

        QuestionSet questionSet = new QuestionSet(subject, totalQuestions, questionCountDetails);

        Exam exam = new Exam(examId, examName, subject, examNote, date, examTime, questionSet);
        sc.nextLine();

        System.out.println("Enter exam's name");
        String examFileName = sc.nextLine();

        if (examRepository.addExam(exam, examFileName)) {
            System.out.println("Exam added successfully.");
        } else {
            System.out.println("Failed to add exam.");
        }
    }

    public static void DisplayExamResult(Subject subject, Scanner sc) {

        System.out.println("Nhap ten class:");
        String clazz = sc.nextLine();

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
        ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
                clazz, date);
        System.out.println("Questions List:");
        examRecordRepository.displaySummaryResults();
        System.out.println("Exams List");
        examRecordRepository.listAllExamRecordsCreated();
        System.out.println("Enter the Name of the exam");
        String examname = sc.nextLine();
        examRecordRepository.displayExamRecord(examname);
    }

}
