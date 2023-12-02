import java.util.Scanner;

import classes.function.GeneralFunction;
import classes.user.Account;
import classes.util.Menu;

public class Main {

	static Scanner userInput = new Scanner(System.in);
	static String role = null;
	static Account currentAccount = null;

	public static void main(String[] args) {

		GeneralFunction.createDataDir();

		switch (Menu.welcome(userInput)) {
		case "1":
			Menu.signIn();
			currentAccount = GeneralFunction.signIn(userInput);
			break;
		case "2":
			Menu.signUp();
			currentAccount = GeneralFunction.signUp(userInput);
			break;
		default:
			System.out.println("Exiting...");
			break;
		}

//		if (currentAccount instanceof Admin) {
//			switch (Menu.adminDashboard()) {
//			case "plapla":
//				AdminFunction.AccountManagement();
//				break;
//			case "haha":
//				// Other functions
//				break;
//			}
//		} else if (currentAccount instanceof Professor) {
//			switch (Menu.professorDashboard()) {
//			case "hehe":
//				ProfessorFunction.QuestionManagement();
//				break;
//			case "hihi":
//				ProfessorFunction.ExamManagement();
//				break;
//			}
//		} else {
//			switch (Menu.studentDashboard()) {
//			case "huhu":
//				StudenFunction.takeExam();
//				break;
//			case "hihi":
//				ProfessorFunction.viewResult();
//				break;
//			}
//		}
	}

}
