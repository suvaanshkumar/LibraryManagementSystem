package rLogin_Data;


//Entity Class to store login info
public class LoginCredential {
	private int id;
	private String password, username;
	
	public LoginCredential(int id, String username, String password) {
		setID(id);
		setPassword(password);
		setUsername(username);
	}
	
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isPassword(String password) {
		return (this.password.equals(password));
	}
	
	public int getID() {
		return id;
	}
	
	
	
	
}
