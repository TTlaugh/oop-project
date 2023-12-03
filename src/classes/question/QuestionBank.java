package classes.question;

import java.util.ArrayList;

import classes.util.CustomList;

public class QuestionBank implements CustomList {

	private ArrayList<Object> arr;

	public QuestionBank() {
		this.arr = new ArrayList<Object>();
	}

	public QuestionBank(ArrayList<Object> arr) {
		this.arr = arr;
	}

	@Override
	public ArrayList<Object> getArr() {
		return arr;
	}

	@Override
	public void setArr(ArrayList<Object> arr) {
		this.arr = arr;
	}

	@Override
	public void add(Object ques) {
		this.arr.add((Question) ques);
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
	public void set(int index, Object ques) {
		this.arr.set(index, (Question) ques);
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
	public int findIndex(Object ques) {
		return this.arr.indexOf((Question) ques);
	}

	@Override
	public int findIndex(String question) {
		for (int i = 0; i < arr.size(); i++)
			if (((Question) arr.get(i)).getContent().equals(question))
				return i;
		return -1;
	}

	@Override
	public boolean isObjectAdded(Object ques) {
		return this.arr.contains((Question) ques);
	}

}
