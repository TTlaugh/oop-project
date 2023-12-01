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

	public static boolean createDataDir() {
		File dir = new File(Constant.dataPath.data_BaseDir);
		if (!dir.exists())
			return dir.mkdirs();
		return false;
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
			String year = CheckInput.toStrNumberic(userInput.nextLine(), 1950, Year.now().getValue() - 6);
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
			newAccount = new Admin();
			break;
		case "professor":
			newAccount = new Professor();
			break;
		case "student":
			newAccount = new Student();
			break;
		default:
			break;
		}
		newAccount.setUsername(username);
		newAccount.setPassword(password);
		newAccount.setRole(role);
		newAccount.setInfo(info);

		if (accountRepository.addUser(newAccount)) {
			System.out.println("Registration successful!");
			return newAccount;
		}
		System.out.println("Registration failed. Username already exists.");
		return null;
	}

}
