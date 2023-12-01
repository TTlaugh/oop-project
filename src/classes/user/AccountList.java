package classes.user;

import java.util.ArrayList;

public class AccountList {

	private ArrayList<Account> arr;

	public AccountList() {
		this.arr = new ArrayList<Account>();
	}

	public AccountList(ArrayList<Account> acclist) {
		this.arr = acclist;
	}

	public ArrayList<Account> getArr() {
		return arr;
	}

	public void setArr(ArrayList<Account> acclist) {
		this.arr = acclist;
	}

	public void add(Account acc) {
		this.arr.add(acc);
	}

	public void remove(int index) {
		this.arr.remove(index);
	}

	public Account get(int index) {
		return this.arr.get(index);
	}

	public void set(int index, Account acc) {
		this.arr.set(index, acc);
	}

	public void clear() {
		this.arr.clear();
	}

	public int size() {
		return this.arr.size();
	}

	public int findIndex(Account acc) {
		return this.arr.indexOf(acc);
	}

	public int findIndex(String username) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).getUsername().equals(username))
				return i;
		return -1;
	}

	public boolean isAccountRegistered(Account acc) {
		return findIndex(acc.getUsername()) != -1;
	}
}