package Gui;


import java.io.IOException;

import Controllers.NewAuctionController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * 
 */
public class NewAuction {
	/**
	 * Creates modal window in which admin can input data and register an auction 
	 */
	public static void NewAuc() {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		GridPane root = new GridPane();
		
		root.setVgap(5);
		root.setHgap(5);
		
		Label n = new Label("Name:");
		n.setFont(new Font("Arial", 20));
		TextField name = new TextField ();
		name.setPromptText("Enter name:");
		name.setFont(new Font("Arial", 20));
		
		Label c = new Label("Collection:");
		c.setFont(new Font("Arial", 20));
		TextField collection = new TextField();
		collection.setPromptText("Enter collection:");
		collection.setFont(new Font("Arial", 20));
		
		Label p = new Label("Start Price:");
		p.setFont(new Font("Arial", 20));
		TextField price = new TextField();
		price.setPromptText("Enter price:");
		price.setFont(new Font("Arial", 20));
		
		Label t = new Label("Schedule:");
		t.setFont(new Font("Arial", 20));
		TextField time = new TextField();
		time.setFont(new Font("Arial", 20));
		time.setPromptText("Set time:");
		
		Button reg = new Button("Register");
		reg.setFont(new Font("Arial", 20));
		
		//---------------------------
		Alert alEx = new Alert(Alert.AlertType.INFORMATION);
		
		alEx.setTitle("Register error");
		alEx.setHeaderText("This Auction is already Registered");
		alEx.setContentText("");
		
		Alert wrDa = new Alert(Alert.AlertType.INFORMATION);
		
		wrDa.setTitle("Register error");
		wrDa.setHeaderText("Incorrect data");
		wrDa.setContentText("Please correct your input data(check price and time)");
		//------------------------------
		
		root.add(n, 5, 5);
		root.add(name, 15,  5);
		root.add(c, 5, 15);
		root.add(collection,  15, 15);
		root.add(p, 5, 25);
		root.add(price, 15, 25);
		root.add(t, 5, 35);
		root.add(time, 15, 35);
		root.add(reg, 5, 45);
		
		reg.setOnAction(e1->{
			try {
				NewAuctionController.Reg(name.getText(), collection.getText(), price.getText(), time.getText(), alEx, wrDa, stage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		
		Scene scene = new Scene(root);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.setScene(scene);
		stage.setTitle("Create new Auction");
		stage.showAndWait();
	}
}
