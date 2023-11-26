package classes.function;

import classes.repository.*;
import classes.user.*;

import java.util.Scanner;

public class Login {
	public static void loginScreen() {
		try (Scanner sc = new Scanner(System.in)) {
			AccountRepository accountRepository = new AccountRepository("acclist.txt");

			while (true) {
				System.out.println("[1].DANG NHAP");
				System.out.println("[2].DANG KY");
				System.out.println("[3].EXIT");
				System.out.println("NHAP LUA CHON: ");
				int choice = sc.nextInt();
				sc.nextLine(); 

				switch (choice) {
				case 1:
					System.out.println("Nhap user name: ");
					String loginUsername = sc.nextLine();
					System.out.println("Nhap password: ");
					String loginPassword = sc.nextLine();

					Account loggedInAccount = accountRepository.findUserByUserName(loginUsername);

					if (loggedInAccount != null && loggedInAccount.getPassword().equals(loginPassword)) {
						System.out.println("Login successful!");
						if (loggedInAccount.getRole().equalsIgnoreCase("admin")) { 
							System.out.println("Đăng nhập thành công với tài khoản admin!");
						}
					}
					// if else(){

					// }

					else {
						System.out.println("Login failed. Invalid username or password.");
					}
					break;

				case 2:
					System.out.println("Nhap user name: ");
					System.out.println("Professor: <faculty><subjectId><count> Example: IT001001");
					System.out.println("Student: <faculty><year><class><orderNumberInClass> Example: IT20224001");
					System.out.println("Admin: admin");
					String username = sc.nextLine();

					System.out.println("Nhap password: ");
					String password = sc.nextLine();

					System.out.println("Nhap role: ");
					String role = sc.nextLine();

					System.out.println("Nhap full name: (vd:Nguyen_Van_A)");
					String fullName = sc.nextLine();

					System.out.println("Nhap nam sinh: ");
					int yearOfBirth = sc.nextInt();

					sc.nextLine(); 

					System.out.println("Nhap gioi tinh: ");
					String gender = sc.nextLine();

					System.out.println("Nhap sdt: ");
					String phoneNumber = sc.nextLine();

					Account acc = null;
					UserInfo info = new UserInfo(fullName, yearOfBirth, gender, phoneNumber);
					if (role.equalsIgnoreCase("professor")) {
						acc = new Professor() {}; 
					} else if (role.equalsIgnoreCase("student")) {
						acc = new Student() {}; 
					} if (role.equalsIgnoreCase("admin")) {
						acc = new Admin() {}; 
					}

					acc.setUsername(username); 
					acc.setPassword(password); 
					acc.setRole(role); 
					acc.setInfo(info);

					System.out.println(acc);

					if (accountRepository.addUser(acc)) {
						System.out.println("Registration successful!");
					} else {
						System.out.println("Registration failed. Username already exists.");
					}
					break;

				case 3:
					accountRepository.saveList();
					System.out.println("Exiting program.");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid choice. Please choose a valid option.");
					break;
				}
			}
		}
	}
}
