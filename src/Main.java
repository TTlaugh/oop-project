import java.util.Scanner;

import classes.function.AdminFunction;
import classes.function.GeneralFunction;
import classes.function.ProfessorFunction;
import classes.function.StudentFunction;
import classes.user.Account;
import classes.user.Admin;
import classes.user.Professor;
import classes.user.Student;
import classes.util.Menu;

public class Main {

	static Scanner userInput = new Scanner(System.in);
	static Account currentAccount = null;
	static boolean loginSession = true;

	public static void main(String[] args) {

		if (!GeneralFunction.createDataDir()) {
			System.out.println("Fail to generate data folder");
			return;
		}
		if (!GeneralFunction.canReadSubjectData()) {
			System.out.println("Does not have subject data");
			return;
		}

		do {
			GeneralFunction.clearScreen();
			switch (Menu.welcome(userInput)) {
			case "1":
				Menu.signIn();
				currentAccount = GeneralFunction.signIn(userInput);
				GeneralFunction.pressAnyKeyToContinue(userInput);
				break;
			case "2":
				Menu.signUp();
				currentAccount = GeneralFunction.signUp(userInput);
				GeneralFunction.pressAnyKeyToContinue(userInput);
				break;
			default:
				System.out.println("Exiting...");
				loginSession = false;
				currentAccount = null;
			}

			while (currentAccount != null) {
				GeneralFunction.clearScreen();
				if (currentAccount instanceof Admin) {
					String choice = Menu.adminDashboard(userInput);
					if (choice.equals("exit"))
						break;
					AdminFunction.accountManager(userInput, choice);
				} else if (currentAccount instanceof Professor) {
					String choice = "exit";
					switch (Menu.professorDashboard(userInput)) {
					case "1":
						choice = Menu.questionManager(userInput);
						ProfessorFunction.questionManager(userInput, (Professor) currentAccount, choice);
						break;
					case "2":
						choice = Menu.examManager(userInput);
						ProfessorFunction.examManager(userInput, (Professor) currentAccount, choice);
						break;
					}
					if (choice.equals("exit"))
						break;
				} else if (currentAccount instanceof Student) {
					String choice = "exit";
					switch (choice = Menu.studentDashboard(userInput)) {
					case "1":
						StudentFunction.takeExam(userInput, (Student) currentAccount);
						break;
					case "2":
						StudentFunction.viewResult(userInput, (Student) currentAccount);
						break;
					}
					if (choice.equals("exit"))
						break;
				}

				System.out.println();
				GeneralFunction.pressAnyKeyToContinue(userInput);
			}
		} while (loginSession);
	}

}
