package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Users.Admin;
import Users.Guest;
import Users.User;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Controller for Login
 * */
public class LoginController {
	/**
	 * Checking files Admins.txt and Guests.txt for suck login and password, 
	 * logs in if such user was found, else throws error "User's not found" 
	 * @param user
	 * @param file
	 * @param login
	 * @param password
	 * @param win
	 * @return
	 * @throws Exception
	 */
	public static User Checklist(User user, String file, TextField login,
			TextField password, Stage win) throws Exception{
		File dir = new File("D://PrVerPrjKokin");
		if (dir.exists() == false) {
			if(dir.mkdir() == true)System.out.println("New folder has been created");
			else System.out.println("Folder hasn't been created");
		}
		File file1 = new File(dir, file);
		if (file1.exists() == false) {
			if(file1.createNewFile() == true)System.out.println("File Guests.txt has been created");
			else System.out.println("File Guests.txt hasn't been created");
		}
		FileReader Check = new FileReader(file1); 
		Scanner scan = new Scanner(Check);
		
		//-------------------------
		Alert pass = new Alert(Alert.AlertType.INFORMATION);
		
		pass.setTitle("Log In error");
		pass.setHeaderText("Password is incorrect");
		pass.setContentText("This is wrong password");
		
		//-------------------------
		
		String log = login.getText();
		String compare;
		
		if(scan.hasNextLine() == true) {
			try{
				while(scan.hasNext() == true){
					compare = scan.next();
					//System.out.println(compare + " " +file);
					if(compare.compareTo(log) == 0) {
						//System.out.println("Success");

						compare = scan.next();
						compare = compare.trim();

						if(compare.compareTo(password.getText()) == 0) {
							if(file == "Guests.txt"){
								user = new Guest(login.getText(), password.getText());
								compare = scan.next();
								user.setTitul();
								user.setWal(Integer.parseInt(compare));
								//System.out.println("Guest has been created");
							}
							else if (file == "Admins.txt") {
								user = new Admin(login.getText(), password.getText());
								user.setTitul();
								//System.out.println("Admin has been created");
							}
							Check.close();
							scan.close();
							//System.out.println("Success");
							win.close();
							
							return user;
						}
						else {
							pass.showAndWait();
						}
						
					}
					else{

						compare = scan.nextLine();
						//System.out.println(compare);
					}
					
				}
				
				
			}
			catch(NoSuchElementException | IOException e1) {
				
			}
		}
		
		Check.close();
		scan.close();
		win.close();
		
		return user;
	}
}
