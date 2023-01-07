package Gui;


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
 * Modal window with signup proccess
 */
public class AdminSignup {
	/**
	 * Opens window to sign up admin, there are 3 fields to put sign up data and button "Sign Up"
	 * @throws IOException
	 */
	public static void SignupWindow() throws IOException{
		
		//-------------------------------------------------------
		Alert logRep = new Alert(Alert.AlertType.INFORMATION);

		logRep.setTitle("Sign Up error");
		logRep.setHeaderText("Login already taken");
		logRep.setContentText("User with such login already exist!");
		
		    
		Alert pass = new Alert(Alert.AlertType.INFORMATION);
		
		pass.setTitle("Sign Up error");
		pass.setHeaderText("Passwords aren't identic");
		pass.setContentText("Passwords must be identic");
		//----------------------------------------------------------
		
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
				SignupController.SignUp("Admins.txt", login, logRep, password, passwordCheck, Swindow, pass);
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
