package classes.util;

import java.util.Scanner;

public class Menu {

	public static String welcome(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║ QUIZ EXAM MANAGEMENT SYSTEM   ║\n"
				    + "\t║     (for IT department)       ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Sign in           [2] Sign up\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to exit\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 2);
		return chosen == null ? "exit" : chosen;
	}
	
	public static void signIn() {
		String text = ""
				+ "Sign in to QMS\n";
		System.out.print(text);
	}
	public static void signUp() {
		String text = ""
				+ "Welcome to QMS!\n"
				+ "Let's start by registering an account\n";
		System.out.print(text);
	}

	public static String adminDashboard(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║             Admin             ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Display user list\n"
				    + "\t[2] Edit user infor\n"
				    + "\t[3] Delete user\n"
				    + "\n"
				    + "Please choose 1 of the 3 options above or enter other content to logout\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 3);
		return chosen == null ? "exit" : chosen;
	}
	public static void displayUserList() {
		String text = ""
				+ "Display user's list\n";
		System.out.print(text);
	}
	public static void addUser() {
		String text = ""
				+ "Add new user\n";
		System.out.print(text);
	}
	public static void editUser() {
		String text = ""
				+ "Edit user infor\n";
		System.out.print(text);
	}
	public static void deleteUser() {
		String text = ""
				+ "Delete user\n";
		System.out.print(text);
	}
	public static void logOut() {
		String text = ""
				+ "Logout successfully\n";
		System.out.print(text);
	}
	public static String editUser(Scanner input) {
		String text = "\n"
				    + "\t[1] Full Name      [2] Year of Birth\n"
				    + "\t[3] Gender         [4] Phone Number\n"
				    + "\n"
				    + "Enter the number of the information you want to edit\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 4);
		return chosen == null ? "exit" : chosen;
	}
	
	
	
	public static String professorDashboard(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║            Professor          ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Question menu     	[2] Exam menu\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to logout\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 2);
		return chosen == null ? "exit" : chosen;
	}
	public static String questionManager(Scanner input) {
		String text = "\t╔══════════════════════════════╗\n"
				    + "\t║       Question Manager       ║\n"
				    + "\t╚══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Show question bank\n"
				    + "\t[2] Add question\n"
				    + "\t[3] Search question (edit, remove)\n"
				    + "\n"
				    + "Please choose 1 of the 3 options above or enter other content to exit\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 3);
		return chosen == null ? "exit" : chosen;
	}
	
	public static String examManager(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║           Exam Manager        ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Create Exams       [2] Preview exam\n"
				    + "\t[3] Add exam           [4] Delete exam\n"
				    + "\t[5] Transcript         [6] View exam record\n"
				    + "\n"
				    + "Please choose 1 of the 6 options above or enter other content to exit\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 6);
		return chosen == null ? "exit" : chosen;
	}
	public static String searchQuestion(Scanner input) {
		String text = "\t[Filter] Search by:\n"
				    + "\t[1] Content   " + "[2] Chapter   " + "[3] Difficulty\n"
				    + "\n"
				    + "Please choose 1 of the 3 options above or enter other content to exit\n"
				    + " ";
		System.out.print(text);
		String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 3);
		return chosen == null ? "exit" : chosen;
	}
	public static String modifyQuestion(Scanner input) {
		String text = "\tChoose modification method:\n"
					+ "\n"
				    + "\t[1] Modify by content    [2] Modify by chapter\n"
				    + "\t[3] Modify by difficulty\n"
				    + "\n"
				    + "Please choose 1 of the 3 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 3);
	}
	public static String studentDashboard(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║             Student           ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Do exam       [2] View Result\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to logout\n"
				    + " ";
	System.out.print(text);
	String chosen = CheckInput.toStrNumberic(input.nextLine(), 1, 2);
	return chosen == null ? "exit" : chosen;
	}
}
