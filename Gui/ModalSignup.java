package Gui;

import java.io.File;

import java.io.IOException;


import Controllers.SignupController;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * 
 */
public class ModalSignup {
	/**
	 * Modal window in which user can input data and register himself 
	 * as a guest(from programGUI)
	 * or register a new admin (from Admin menu)
	 * @param choice
	 * @throws IOException
	 */
	public static void SignupWindow(String choice) throws IOException{
		File dir = new File("D://PrVerPrjKokin");
		if (dir.exists() == false) {
			if(dir.mkdir() == true)System.out.println("New folder has been created");
			else System.out.println("Folder hasn't been created");
		}
		File file1 = new File(dir, "Guests.txt");
		if (file1.exists() == false) {
			if(file1.createNewFile() == true)System.out.println("File Guests.txt has been created");
			else System.out.println("File Guests.txt hasn't been created");
		}
		File file2 = new File(dir, "Admins.txt");
		if (file2.exists() == false) {
			if(file2.createNewFile() == true)System.out.println("File Admins.txt has been created");
			else System.out.println("File Admins.txt hasn't been created");
		}
		
		
		Alert logRep = new Alert(Alert.AlertType.INFORMATION);

		logRep.setTitle("Sign Up error");
		logRep.setHeaderText("Login already taken");
		logRep.setContentText("User with such login already exist!");
		
		    
		Alert pass = new Alert(Alert.AlertType.INFORMATION);
		
		pass.setTitle("Sign Up error");
		pass.setHeaderText("Passwords aren't identic");
		pass.setContentText("Passwords must be identic");

		Stage Swindow = new Stage();
		Swindow.setResizable(false);
		Swindow.initModality(Modality.APPLICATION_MODAL);
		
		GridPane pane = new GridPane();
		pane.setVgap(2);
		pane.setHgap(2);
		
		TextField passwordCheck = new TextField();
		passwordCheck.setPromptText("Password again");
		TextField login = new TextField();
		login.setPromptText("Login");
		TextField password = new TextField();
		password.setPromptText("Password");
		Button aut = new Button("Sign Up");
		
		pane.add(login, 10, 10);
		pane.add(password, 10, 20);
		pane.add(passwordCheck, 15, 20);
		pane.add(aut, 10, 30);
		
		
		aut.setOnAction(e ->{
			try {
				SignupController.SignUp(choice, login, logRep, password, passwordCheck, Swindow, pass);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		Scene scene = new Scene(pane, 400, 200);
		Swindow.setWidth(400);
		Swindow.setHeight(200);
		Swindow.setScene(scene);
		Swindow.setTitle("Sign Up");
		Swindow.showAndWait();
		return;
	}
}
