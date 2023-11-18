package classes.repository;

import classes.user.*;

import java.io.*;
import java.util.*;

public class AccountRepository {

	private AccountList acclist;
	private String filepath;
	private File repofile;
	
	public AccountRepository() {
		this.acclist = new AccountList();
		this.filepath = null;
		this.repofile = null;
	}
	public AccountRepository(String filepath) {
		this.acclist = new AccountList();
		this.filepath = filepath;
		this.repofile = new File(this.filepath);
		if (!repofile.exists()) createfile();
		loadList();
	}

	private void createfile() {
	    try {
	    	this.repofile = new File(this.filepath);
	    	this.repofile.createNewFile();
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }	
	}
	private void readfile() {
		try {
	    	this.repofile = new File(this.filepath);
	    	if (this.repofile.canRead()) {
	    		Scanner scanner = new Scanner(this.repofile);
	    		while (scanner.hasNext()) {

	    			String username = scanner.next();
	    			String password = scanner.next();
	    			String role = scanner.next();
	    			String fullName = scanner.next();
	    			int yearOfbirth = scanner.nextInt();
	    			String gender = scanner.next();
	    			String phoneNumber = scanner.next();
	    			UserInfo info = new UserInfo(fullName, yearOfbirth, gender, phoneNumber);

	    			if      (role.equalsIgnoreCase("admin"))     this.acclist.add(new Admin(username, password, role, info));
	    			else if (role.equalsIgnoreCase("professor")) this.acclist.add(new Professor(username, password, role, info));
	    			else if (role.equalsIgnoreCase("student"))   this.acclist.add(new Student(username, password, role, info));
	    		}
	    		scanner.close();
	    	}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	private void writefile() {
		try {
			FileWriter writer = new FileWriter(this.filepath);
			for (Account acc : this.acclist.getAccList()) {
				writer.write(
					acc.getUsername()             +" "+
					acc.getPassword()             +" "+
					acc.getRole()                 +" "+
					acc.getInfo().getFullName()   +" "+
					acc.getInfo().getYearOfBirth()+" "+
					acc.getInfo().getGender()     +" "+
					acc.getInfo().getPhoneNumber()+"\n"
				);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void loadList() {
		readfile();
	}
	public void saveList() {
		writefile();
	}

	public boolean addUser(Account acc) {
		if (acclist.isAccountRegistered(acc)) return false;
		if      (acc instanceof Admin)     this.acclist.add(new Admin((Admin)acc));
		else if (acc instanceof Professor) this.acclist.add(new Professor((Professor)acc));
		else if (acc instanceof Student)   this.acclist.add(new Student((Student)acc));
		saveList();
		return true;
	}
	public boolean removeUser(String username) {
		int index = acclist.findIndex(username);
		if (index == -1) return false;
		acclist.remove(index);
		return true;
	}
	public boolean changeUserInfo(String username, Account acc) {
		int index = acclist.findIndex(username);
		if (index == -1) return false;
		acclist.set(index, acc);
		return true;
	}
	public Account findUserByUserName(String username) {
		int index = acclist.findIndex(username);
		if (index == -1) return null;
		return acclist.get(index);
	}

}
