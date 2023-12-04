package classes.function;

import java.io.File;
import java.time.Year;
import java.util.Scanner;

import classes.repository.AccountRepository;
import classes.user.Account;
import classes.user.Admin;
import classes.user.Professor;
import classes.user.Student;
import classes.user.UserInfo;
import classes.util.CheckInput;
import classes.util.Constant;

public class GeneralFunction {

	public static void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	public static boolean createDataDir() {
		File baseDir = new File(Constant.dataPath.data_BaseDir);
		File questionBanksDir = new File(Constant.dataPath.QuestionBanks_Dir);
		File examsDir = new File(Constant.dataPath.Exams_Dir);
		File examRecordsDir = new File(Constant.dataPath.ExamRecords_Dir);
		baseDir.mkdirs();
		questionBanksDir.mkdirs();
		examsDir.mkdirs();
		examRecordsDir.mkdirs();
		return baseDir.exists() && questionBanksDir.exists() && examsDir.exists() && examRecordsDir.exists();
	}

	public static boolean canReadSubjectData() {
		File file = new File(Constant.dataPath.SubjectList_File);
		if (!file.exists())
			return false;
		else if (file.length() == 0)
			return false;
		return true;
	}

	public static Account signIn(Scanner userInput) {
		AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
		System.out.println("Enter your student ID (professor ID)");
		System.out.print(" ");
		String username = userInput.nextLine();
		System.out.println("Enter your password");
		System.out.print(" ");
		String password = userInput.nextLine();

		Account loggedInAccount = accountRepository.findUserByUserName(username);
		if (loggedInAccount != null && loggedInAccount.getPassword().equals(password)) {
			if (loggedInAccount.getRole().equalsIgnoreCase("admin")) {
				System.out.println("Successfully logged in with Admin account");
			} else if (loggedInAccount.getRole().equalsIgnoreCase("professor")) {
				System.out.println("Successfully logged in with Professor account");
			} else {
				System.out.println("Successfully logged in with Student account");
			}
			return loggedInAccount;
		}
		System.out.println("Login failed. Invalid username or password.");
		return null;
	}

	public static Account signUp(Scanner userInput) {

		AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
		Account newAccount = null;
		String username = null;
		String role = null;
		String password = null;
		UserInfo info = new UserInfo();

		do {
			System.out.println("Please enter your student ID (professor ID)");
			System.out.print(" ");
			username = userInput.nextLine();
			role = CheckInput.toRole(username);
			if (role == null)
				System.out.println("This ID is invalid");
		} while (role == null);
		System.out.println("Create a password");
		System.out.print(" ");
		password = userInput.nextLine();
		do {
			System.out.println("Enter your full name (Ex: First_Mid_LastName)");
			System.out.print(" ");
			info.setFullName(CheckInput.toFullName(userInput.nextLine()));
			if (info.getFullName() == null)
				System.out.println("You entered the name in the wrong format");
		} while (info.getFullName() == null);
		do {
			System.out.println("Enter your year of birth");
			System.out.print(" ");
			String year = CheckInput.toStrNumberic(userInput.nextLine(), Year.now().getValue() - 70,
					Year.now().getValue() - 18);
			info.setYearOfBirth(year == null ? -1 : Integer.parseInt(year));
			if (info.getYearOfBirth() == -1)
				System.out.println("Invalid year");
		} while (info.getYearOfBirth() == -1);
		System.out.println("Male or Female or Other?");
		System.out.print(" ");
		info.setGender(CheckInput.toGender(userInput.nextLine()));
		do {
			System.out.println("Enter your phone number");
			System.out.print(" ");
			info.setPhoneNumber(CheckInput.toPhoneNumber(userInput.nextLine()));
			if (info.getPhoneNumber() == null)
				System.out.println("Invalid phone number in Viet Nam");
		} while (info.getPhoneNumber() == null);

		switch (role) {
		case "admin":
			newAccount = new Admin(username, password, role, info);
			break;
		case "professor":
			newAccount = new Professor(username, password, role, info);
			break;
		case "student":
			newAccount = new Student(username, password, role, info);
			break;
		default:
			break;
		}

		if (accountRepository.addUser(newAccount)) {
			System.out.println("Registration successful!");
			return newAccount;
		}
		System.out.println("Registration failed. Username already exists.");
		return null;
	}

}