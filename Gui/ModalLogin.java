package Gui;


import Controllers.LoginController;

import Users.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Modal window with fields Login and Password
 * Creates static user that will contain admin or guest after login 
 */
public class ModalLogin {
	static User user;
	
	/**
	 * Opens modal window in which you put your login data 
	 * @return
	 * @throws Exception
	 */
	public static User LoginWindow() throws Exception{
		user = null;
		
		Stage Lwindow = new Stage();
		Lwindow.setResizable(false);
		Lwindow.initModality(Modality.APPLICATION_MODAL);
		
		GridPane pane = new GridPane();
		
		pane.setVgap(2);
		pane.setHgap(2);
		
		Label Login = new Label("Login: ");
		Label Password = new Label("Password: ");
		TextField login = new TextField();
		TextField password = new TextField();
		Button aut = new Button("Log In");
		
		
		pane.add(Login, 10, 10);
		pane.add(login, 25, 10);
		pane.add(Password, 10, 20);
		pane.add(password, 25, 20);
		pane.add(aut, 25, 25);
		
		Alert logErr = new Alert(Alert.AlertType.INFORMATION);

		logErr.setTitle("Log In error");
		logErr.setHeaderText("User doesn't exist");
		logErr.setContentText("User with such login does not exist");
		
		aut.setOnAction(e ->{
			try {
				user = LoginController.Checklist(user, "Guests.txt", login, password, Lwindow);
				if(user ==null) user = LoginController.Checklist(user, "Admins.txt", login, password, Lwindow);
				if(user==null)logErr.showAndWait();
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			}
		);
		
		
		Scene scene = new Scene(pane, 300, 200);
		Lwindow.setWidth(300);
		Lwindow.setHeight(200);
		Lwindow.setScene(scene);
		Lwindow.setTitle("Log In");
		Lwindow.showAndWait();
		return user;
	}
}
