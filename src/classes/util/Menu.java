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

}
