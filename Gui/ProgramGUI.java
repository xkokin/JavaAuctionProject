package Gui;

import java.io.IOException;

import Controllers.ProgramController;
import javafx.application.Application;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * 
 * 
 */
public class ProgramGUI extends Application{
	private Button login = new Button("Log in");
	private Button signup = new Button("Sign up");
	private Label centerLabel = new Label("Log in or Sign up");
	
	
	
	/**
	 * class that starts program with authentification or registration of a new user
	 */
	public void start(Stage primaryStage) throws Exception {
		centerLabel.setAlignment(Pos.CENTER);
		GridPane root = new GridPane();
		root.setVgap(5);
		root.setHgap(5);
		//root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root);
		root.add(centerLabel, 20, 10);
		root.add(login, 10, 20);
		root.add(signup, 30, 20);

		
		/*
		 * calls controller that provides login 
		 */
		login.setOnAction(e ->
			{
				try {
					ProgramController.Login(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		);
		/*
		 * calls controller that provides signup 
		 */
		signup.setOnAction(e ->
			{

				try {
					ProgramController.Signup();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setWidth(400);
		primaryStage.setHeight(200);
		primaryStage.setTitle("Log in/Sign up");
	    primaryStage.show();
	}
	/**
	 * Starts the starting window of the program with the choice of logging in or signing up
	 * */
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
