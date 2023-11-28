package classes.subject;

public class Subject {

	private String id;
	private String name;

	public Subject() {
		this.id = null;
		this.name = null;
	}

	public Subject(String id) {
		this.id = id;
		/*
		 * Khai bao mot Object SubjectList. Check trong SubjectList va lay ten mon hoc
		 * gan cho this.name
		 */
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
