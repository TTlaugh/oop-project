package classes.user;

import java.util.ArrayList;

import classes.util.CustomList;

public class AccountList implements CustomList {

	private ArrayList<Object> arr;

	public AccountList() {
		this.arr = new ArrayList<Object>();
	}

	public AccountList(ArrayList<Object> acclist) {
		this.arr = acclist;
	}

	public ArrayList<Object> getArr() {
		return arr;
	}

	public void setArr(ArrayList<Object> acclist) {
		this.arr = acclist;
	}

	@Override
	public void add(Object acc) {
		this.arr.add((Account) acc);
	}

	@Override
	public void remove(int index) {
		this.arr.remove(index);
	}

	@Override
	public Object get(int index) {
		return this.arr.get(index);
	}

	@Override
	public void set(int index, Object acc) {
		this.arr.set(index, (Account) acc);
	}

	@Override
	public void clear() {
		this.arr.clear();
	}

	@Override
	public int size() {
		return this.arr.size();
	}

	@Override
	public int findIndex(Object acc) {
		return this.arr.indexOf((Account) acc);
	}

	@Override
	public int findIndex(String username) {
		for (int i = 0; i < this.arr.size(); i++)
			if (((Account) this.arr.get(i)).getUsername().equals(username))
				return i;
		return -1;
	}

	@Override
	public boolean isObjectAdded(Object acc) {
		return findIndex(((Account) acc).getUsername()) >= 0;
	}
}