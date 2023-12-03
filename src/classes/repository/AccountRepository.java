package classes.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.user.Account;
import classes.user.AccountList;
import classes.user.Admin;
import classes.user.Professor;
import classes.user.Student;
import classes.user.UserInfo;
import classes.util.CustomList;
import classes.util.FileHandling;

public class AccountRepository {

	private CustomList acclist;
	private String filepath;

	public AccountRepository(String filepath) {
		this.acclist = new AccountList();
		this.filepath = filepath;
		if (!FileHandling.createFile(filepath) || !loadList()) {
			this.acclist = null;
			this.filepath = null;
		}
	}

	public boolean readFile() {
		try {
			File repofile = new File(this.filepath);
			if (repofile.canRead()) {
				try (Scanner scanner = new Scanner(repofile)) {
					while (scanner.hasNextLine()) {

						String username = scanner.next();
						String password = scanner.next();
						String role = scanner.next();
						String fullName = scanner.next();
						int yearOfbirth = scanner.nextInt();
						String gender = scanner.next();
						String phoneNumber = scanner.next();
						scanner.nextLine();
						UserInfo info = new UserInfo(fullName, yearOfbirth, gender, phoneNumber);

						if (role.equalsIgnoreCase("admin"))
							this.acclist.add(new Admin(username, password, role, info));
						else if (role.equalsIgnoreCase("professor"))
							this.acclist.add(new Professor(username, password, role, info));
						else if (role.equalsIgnoreCase("student"))
							this.acclist.add(new Student(username, password, role, info));
					}
				}
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean writeFile() {
		try {
			FileWriter writer = new FileWriter(this.filepath);
			for (Object acc : this.acclist.getArr()) {
				writer.write(((Account) acc).getUsername() + " " + ((Account) acc).getPassword() + " "
						+ ((Account) acc).getRole() + " " + ((Account) acc).getInfo().getFullName() + " "
						+ ((Account) acc).getInfo().getYearOfBirth() + " " + ((Account) acc).getInfo().getGender() + " "
						+ ((Account) acc).getInfo().getPhoneNumber() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean loadList() {
		return readFile();
	}

	public boolean saveList() {
		return writeFile();
	}

	public boolean addUser(Account acc) {
		if (acclist.isObjectAdded(acc))
			return false;
		if (acc instanceof Admin)
			this.acclist.add(new Admin((Admin) acc));
		else if (acc instanceof Professor)
			this.acclist.add(new Professor((Professor) acc));
		else if (acc instanceof Student)
			this.acclist.add(new Student((Student) acc));
		return saveList();
	}

	public boolean removeUser(String username) {
		int index = acclist.findIndex(username);
		if (index == -1)
			return false;
		acclist.remove(index);
		return saveList();
	}

	public boolean changeAccountInfo(String username, Account acc) {
		int index = acclist.findIndex(username);
		if (index == -1)
			return false;
		acclist.set(index, acc);
		return saveList();
	}

	public Account findUserByUserName(String username) {
		int index = acclist.findIndex(username);
		if (index == -1)
			return null;
		return (Account) acclist.get(index);
	}

	public AccountList getAcclist() {
		return (AccountList) acclist;
	}

	public void setAcclist(AccountList acclist) {
		this.acclist = acclist;
	}

}
