package classes.user;

public class UserInfo {

	private String fullName;
	private int yearOfBirth;
	private String gender;
	private String phoneNumber;

	public UserInfo() {
		this.fullName = null;
		this.yearOfBirth = 0;
		this.gender = null;
		this.phoneNumber = null;
	}
	public UserInfo(String fullName, int yearOfBirth, String gender, String phoneNumber) {
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "UserInfo [fullName:" + fullName + ", yearOfBirth:" + yearOfBirth + ", gender:" + gender
				+ ", phoneNumber:" + phoneNumber + "]";
	}

}
