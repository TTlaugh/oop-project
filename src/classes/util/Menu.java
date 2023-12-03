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
		return CheckInput.toStrNumberic(input.nextLine(), 1, 2);
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

	public static String menuAdmin(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			  Admin  			 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Display user's list      [2] Add new user\n"
				    + "\t[3] Edit user infor 		  [4] Delete user\n"
				    + "\t[5] Log Out\n"
				    + "\n"
				    + "Please choose 1 of the 5 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 5);
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
				    + "\t[3] Gender 		[4] Phone Number\n"
				    + "\n"
				    + "Please choose 1 of the 4 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 4);
	}
	
	
	
	public static String menuProfessor(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			  Professor  		 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Question menu     	[2] Exam menu\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 2);
	}
	public static String questionmenu(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			Question Menu  		 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Show question bank    	[2] Add question\n"
				    + "\t[3] Delete question		[4] Show question\n"
				    + "\n"
				    + "Please choose 1 of the 4 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 4);
	}
	
	public static String exammenu(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			 Exam Menu  		 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Display exam   	[2] Create exam\n"
				    + "\t[3] Add exam			[4] Delete exam\n"
				    + "\n"
				    + "Please choose 1 of the 4 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 4);
	}
	public static String removeQuestion(Scanner input) {
		String text = "\tChoose delete method\n"
					+ "\n"
				    + "\t[1] Delete by content     [2] Delete by chapter\n"
				    + "\t[3] Delete by difficulty\n"
				    + "\n"
				    + "Please choose 1 of the 3 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 3);
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
	public static String menuStudent(Scanner input) {
		String text = "\t╔═══════════════════════════════╗\n"
				    + "\t║			   Student  		 ║\n"
				    + "\t╚═══════════════════════════════╝\n"
				    + "\n"
				    + "\t[1] Do exam     	[2] Display Result\n"
				    + "\n"
				    + "Please choose 1 of the 2 options above or enter other content to exit\n"
				    + " ";
	System.out.print(text);
	return CheckInput.toStrNumberic(input.nextLine(), 1, 2);
	}
}
