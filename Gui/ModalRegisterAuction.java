package Gui;


import Controllers.RegisterAucController;
import Products.Collection;
import Users.Guest;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author HlibKokin
 * @version 13/05/2022
 * Modal window to register for auction
 */

public class ModalRegisterAuction {
	
	/**
	 * Opens modal window with information about auction and two buttons
	 * @param item
	 * @param guest
	 */
	public static void RegAuc(Collection item, Guest guest) {
		Stage Reg = new Stage();
		Reg.setResizable(false);
		Reg.initModality(Modality.APPLICATION_MODAL);
		
		Alert AlReg = new Alert(Alert.AlertType.INFORMATION);

		AlReg.setTitle("Register error");
		AlReg.setHeaderText("Already registered");
		AlReg.setContentText("You are already registered for that auction!");
		
		Alert NoMon = new Alert(Alert.AlertType.INFORMATION);

		NoMon.setTitle("Register error");
		NoMon.setHeaderText("Not enough money!");
		NoMon.setContentText("You can't register because there are not enough money in your wallet!");
		
		Alert AlB = new Alert(Alert.AlertType.INFORMATION);

		AlB.setTitle("Register error");
		AlB.setHeaderText("You can't register for this auction!");
		AlB.setContentText("Auction has been already started!");
		
		GridPane root = new GridPane();
		
		root.setVgap(5);
		root.setHgap(5);
		
		Label n = new Label("Name:");
		n.setFont(new Font("Arial", 20));
		Label name = new Label(item.GetName());
		name.setFont(new Font("Arial", 20));
		Label c = new Label("Collection:");
		c.setFont(new Font("Arial", 20));
		Label collection = new Label(item.GetCollection());
		collection.setFont(new Font("Arial", 20));
		
		int priceInt = item.GetPrice();
		String i = Integer.toString(priceInt);
		Label p = new Label("Price:");
		p.setFont(new Font("Arial", 20));
		Label price = new Label(i);
		price.setFont(new Font("Arial", 20));
		Label t = new Label("Scheduled:");
		t.setFont(new Font("Arial", 20));
		Label time = new Label(item.GetTime());
		time.setFont(new Font("Arial", 20));
		
		root.add(n, 5, 5);
		root.add(name, 10,  5);
		root.add(c, 5, 15);
		root.add(collection,  10, 15);
		root.add(p, 5, 25);
		root.add(price, 10, 25);
		root.add(t, 5, 35);
		root.add(time, 10, 35);
		
		Label text = new Label("Do you want to register for auction?");
		text.setFont(new Font("Arial", 20));
		Button yes = new Button("Yes");
		yes.setFont(new Font("Arial", 20));
		Button no = new Button("No");
		no.setFont(new Font("Arial", 20));
		
		
		yes.setOnAction(e ->{
			try {
				RegisterAucController.yes(guest, item, AlReg, NoMon, AlB, Reg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		no.setOnAction(e->{
			Reg.close();
		});
		
		
		root.add(text, 5, 45);
		root.add(yes, 5, 50);
		root.add(no, 10, 50);

		
		Scene scene = new Scene(root);
		Reg.setWidth(600);
		Reg.setHeight(500);
		Reg.setScene(scene);
		Reg.setTitle("Register for Auction");
		Reg.showAndWait();
	}
}
