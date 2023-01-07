package Users;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Objects from this class will create auctions and other admins
 */
public class Admin extends User{
	public Admin (String login, String password) {
		super(login, password); 
	}
	
	public void setTitul() {
		this.Titul = "Admin";
	}
	
}
