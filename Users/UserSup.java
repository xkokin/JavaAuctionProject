package Users;

/**
 * @author HlibKokin
 * @version 13/05/2022
 * Abstract class with methods that will be implementated in User class
 */
public abstract class UserSup {
	public abstract void setWal(int am);
	public abstract int getWal();
	
	/**
	 *setTutil will set different tituls(guest/admin) for guests and admins 
	 */
	public abstract void setTitul();
}
