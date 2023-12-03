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
import classes.util.Menu;

public class AdminFunction {

    public static void DisplayUser()
    {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("User list:");
        for (Object acc : accountRepository.getAcclist().getArr()) {
            System.out.println(acc);
        }
    }

    public static void editUser(Scanner userInput) {

        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("Enter the username of the user you want to edit:");
        System.out.print(" ");
        String username = CheckInput.toRole(userInput.nextLine()) ;

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
                    String year = CheckInput.toStrNumberic(userInput.nextLine(), 1950, Year.now().getValue());
                    userInfoToEdit.setYearOfBirth(year == null ? -1 : Integer.parseInt(year));
                    if (userInfoToEdit.getYearOfBirth() == -1)
                        System.out.println("Invalid year");
                    } while (userInfoToEdit.getYearOfBirth() == -1);
                    break;
                case "3":
                do{System.out.println("Enter new gender (Male/Female/Other):");
                    System.out.print(" ");
                    userInfoToEdit.setGender(CheckInput.toGender(userInput.nextLine()));}
                while(userInfoToEdit.getGender() == null);
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

            // Update the user information in the repository
            accountRepository.changeAccountInfo(username, userToEdit);

            System.out.println("User information updated successfully!");
        } else {
            System.out.println("User not found. Unable to edit.");
        }
        
        
    }

    public static void deleteUser(Scanner sc)
    {
        AccountRepository accountRepository = new AccountRepository(Constant.dataPath.accounts_File);
        System.out.println("Enter user name you want to delete: ");
        String deleteUsername = sc.nextLine();
        System.err.println(deleteUsername);

        if (accountRepository.removeUser(deleteUsername)) {
            System.out.println("User has been delete from system!");
        } else {
            System.out.println("Wrong user name!!");
        }
    }

}
