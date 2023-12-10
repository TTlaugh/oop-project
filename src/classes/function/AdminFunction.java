package classes.function;

import java.time.Year;
import java.util.Scanner;

import classes.repository.AccountRepository;
import classes.user.Account;
import classes.user.UserInfo;
import classes.util.CheckInput;
import classes.util.Constant;
import classes.util.Menu;

public class AdminFunction {

	public static boolean accountManager(Scanner userInput, String userChoice) {
		AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
		switch (userChoice) {
		case "1":
			displayUser(accountRepository);
			break;
		case "2":
			editUser(userInput, accountRepository);
			break;
		case "3":
			deleteUser(userInput, accountRepository);
			break;
		default:
			break;
		}
		return true;
	}

	public static void displayUser(AccountRepository accountRepository) {
		System.out.println("User list:\n");
		for (Object acc : accountRepository.getAcclist().getArr()) {
			System.out.println(acc);
		}
	}

	public static void editUser(Scanner userInput, AccountRepository accountRepository) {

		String username = null;
		do {
			System.out.println("Enter the username of the user you want to edit:");
			System.out.print(" ");
			username = userInput.nextLine();
			if (CheckInput.toRole(username) == null)
				System.out.println("Invalid username");
		} while (CheckInput.toRole(username) == null);

		Account userToEdit = accountRepository.findUserByUserName(username);

		if (userToEdit != null) {
			UserInfo userInfoToEdit = userToEdit.getInfo();

			System.out.println("Edit user information for " + username + ":");
			// System.out.println("1. Full Name");
			// System.out.println("2. Year of Birth");
			// System.out.println("3. Gender");
			// System.out.println("4. Phone Number");

			// System.out.print("Enter the number of the information you want to edit: ");
			// String choice = CheckInput.toStrNumberic(userInput.nextLine(),1,4);

			switch (Menu.editUser(userInput)) {
			case "1":
				do {
					System.out.println("Enter your full name (Ex: First_Mid_LastName)");
					System.out.print(" ");
					userInfoToEdit.setFullName(CheckInput.toFullName(userInput.nextLine()));
					if (userInfoToEdit.getFullName() == null)
						System.out.println("You entered the name in the wrong format");
				} while (userInfoToEdit.getFullName() == null);
				break;
			case "2":
				do {
					System.out.println("Enter your year of birth");
					System.out.print(" ");
					String year = CheckInput.toStrNumberic(userInput.nextLine(), Year.now().getValue() - 70,
							Year.now().getValue() - 18);
					userInfoToEdit.setYearOfBirth(year == null ? -1 : Integer.parseInt(year));
					if (userInfoToEdit.getYearOfBirth() == -1)
						System.out.println("Invalid year");
				} while (userInfoToEdit.getYearOfBirth() == -1);
				break;
			case "3":
				System.out.println("Enter new gender (Male/Female/Other):");
				System.out.print(" ");
				userInfoToEdit.setGender(CheckInput.toGender(userInput.nextLine()));
				break;
			case "4":
				do {
					System.out.println("Enter your phone number");
					System.out.print(" ");
					userInfoToEdit.setPhoneNumber(CheckInput.toPhoneNumber(userInput.nextLine()));
					if (userInfoToEdit.getPhoneNumber() == null)
						System.out.println("Invalid phone number in Viet Nam");
				} while (userInfoToEdit.getPhoneNumber() == null);
				break;
			default:
				System.out.println("Invalid choice. No information edited.");
				return;
			}

			userToEdit.setInfo(userInfoToEdit);
			// Update the user information in the repository
			accountRepository.changeAccountInfo(username, userToEdit);

			System.out.println("User information updated successfully!");
		} else {
			System.out.println("User not found. Unable to edit.");
		}

	}

	public static void deleteUser(Scanner userInput, AccountRepository accountRepository) {
		String username = null;
		do {
			System.out.println("Enter the username of the user you want to edit:");
			System.out.print(" ");
			username = userInput.nextLine();
			if (CheckInput.toRole(username) == null)
				System.out.println("Invalid username");
		} while (CheckInput.toRole(username) == null);

		if (accountRepository.removeUser(username)) {
			System.out.println("User has been delete from system!");
		} else {
			System.out.println("Wrong user name!!");
		}
	}

}
