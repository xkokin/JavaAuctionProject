package Controllers;

import Gui.AdminMainMenu;
import Gui.GuestMainMenu;
import Gui.ModalLogin;
import Gui.ModalSignup;
import Users.Admin;
import Users.Guest;

import Users.User;

import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Controller for program with methods that open certain MainMenu for user
 */
public class ProgramController {
	
	/**
	 * after login method is defines what type of user current user is and opens suitable menu for him 
	 * @param primaryStage
	 * @throws Exception
	 */
	public static void Login(Stage primaryStage) throws Exception {
		User user = ModalLogin.LoginWindow();
		
		if(user != null) {
			//System.out.println(user.GetLogin());
			if(user instanceof Guest) {
				Guest guest = new Guest(user.GetLogin(), user.GetPassword());
				
				guest.TopUpwallet(user.getWal());
				primaryStage.close();
				GuestMainMenu.Menu(guest);
			}
			else if(user instanceof Admin) {
				Admin admin = new Admin(user.GetLogin(), user.GetPassword());
				primaryStage.close();
				AdminMainMenu.Menu(admin);
			}

		}
		
	}
	/**
	 * opens sign up window for guests
	 * @throws Exception
	 */
	public static void Signup() throws Exception {
		ModalSignup.SignupWindow("Guests.txt");
		
	}
}
