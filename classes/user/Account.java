package classes.user;

public abstract class Account {

	private String username;
	private String password;
	private String role;
	private UserInfo info;

	public Account() {
		this.username = null;
		this.password = null;
		this.role = null;
		this.info = new UserInfo();
	}
	public Account(String username, String password, String role, UserInfo info) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.info = info;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserInfo getInfo() {
		return info;
	}
	public void setInfo(UserInfo info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "AccountInfo [username:" + username + ", password:" + password + ", role:" + role + "]"+ "\n" + info;
	}
	
}
