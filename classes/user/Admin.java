package classes.user;

public class Admin extends Account {
	
	public Admin() {
		super();
	}
	public Admin(String username, String password, String role, UserInfo info) {
		super(username, password, role, info);
	}
	public Admin(Admin admin) {
		super(admin.getUsername(), admin.getPassword(), admin.getRole(), admin.getInfo());
	}
}
