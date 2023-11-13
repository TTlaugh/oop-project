package classes.subject;

public class Subject {
	
	private int id;
	private String name;
	
	public Subject() {
		this.id = 0;
		this.name = null;
	}
	public Subject(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
