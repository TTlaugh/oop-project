package classes.subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import classes.util.Constant;

public class Subject {

	private String id;
	private String name;

	public Subject() {
		this.id = null;
		this.name = null;
	}

	public Subject(String id) {
		this.id = id;
		if (!findSubjectName()) {
			this.id = null;
			this.name = null;
		}
	}

	private boolean findSubjectName() {
		File file = new File(Constant.dataPath.SubjectList_File);
		if (!file.exists())
			return false;
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				String id = sc.next();
				String name = sc.next();
				if (id.equals(this.id))
					this.name = name;
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}

}
