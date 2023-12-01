import java.util.Scanner;

import classes.function.GeneralFunction;
import classes.util.Menu;

public class Main {

	static Scanner userInput = new Scanner(System.in);
	static String role = null;

	public static void main(String[] args) {

		GeneralFunction.createDataDir();

		switch (Menu.welcome(userInput)) {
		case "1":
			Menu.signIn();
			role = GeneralFunction.signIn(userInput);
			break;
		case "2":
			Menu.signUp();
			role = GeneralFunction.signUp(userInput);
			break;
		default:
			break;
		}

	}

}
