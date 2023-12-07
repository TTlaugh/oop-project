package classes.user;

public class Student extends Account {

	private String faculty;
	private int yearCourse;
	private String clazz;
	private String serial;

	public Student() {
		super();
		this.faculty = null;
		this.yearCourse = 0;
		this.clazz = null;
	}

	public Student(String username, String password, String role, UserInfo info) {
		super(username, password, role, info);
		/*
		 * Student: <faculty><year><class><orderNumberInClass> Example: IT20224001
		 */
		this.faculty = username.replaceAll("[^A-Za-z]+", "");
		char tmp[] = (username.replaceAll("[^0-9]", "")).toCharArray();
		this.yearCourse = Integer.parseInt(String.copyValueOf(tmp, 0, 4));
		this.clazz = String.valueOf(tmp[4]);
		this.serial = String.copyValueOf(tmp, 5, 3);
	}

	public Student(Student student) {
		super(student.getUsername(), student.getPassword(), student.getRole(), student.getInfo());
		this.faculty = student.getFaculty();
		this.yearCourse = student.getYearCourse();
		this.clazz = student.getClazz();
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getYearCourse() {
		return yearCourse;
	}

	public void setYearCourse(int yearCourse) {
		this.yearCourse = yearCourse;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getOrnum() {
		return serial;
	}

	public void setOrnum(String ornum) {
		this.serial = ornum;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tStudentInfo [faculty:" + faculty + ", yearCourse:" + yearCourse + ", class:"
				+ clazz + ", serial:" + serial + "]";
	}

}
