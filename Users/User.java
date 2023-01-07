package Users;

/**
 * @author HlibKokin
 * @version 13/05/2022
 * Class User implements common methods for its sons classes Admin and Guesr
 * 
 */
public class User extends UserSup{
	String Titul;
	String login;
	String password;
	int wal = 0;
	
	public String getTitul() {
		return this.Titul;
	}
	
	public void setTitul() {
		
	}
	public void setWal(int am) {
		this.wal = am;
		
	}
	public int getWal() {
		return this.wal;
	}
	public User(String login, String password) {
		SetLogin(login);
		SetPassword(password);		
	}
	public User() {
		
	}
	public void SetLogin(String login) {
		this.login = login;
	}
	public String GetLogin() {
		return this.login;
	}
	public void SetPassword(String password) {
		this.password = password;
	}
	public String GetPassword() {
		return this.password;
	}
}
