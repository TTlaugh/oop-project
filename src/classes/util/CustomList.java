package classes.util;

import java.util.ArrayList;

public interface CustomList {

	public ArrayList<Object> getArr();

	public void setArr(ArrayList<Object> object);

	public void add(Object object);

	public void remove(int index);

	public Object get(int index);

	public void set(int index, Object object);

	public void clear();

	public int size();

	public int findIndex(Object object);

	public int findIndex(String text);

	public boolean isObjectAdded(Object object);
}
