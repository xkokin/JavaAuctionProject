package Gui;




import java.io.IOException;

import Controllers.GuestMenuController;
import Products.Collection;
import Users.Guest;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Window that is main menu for the guests
 */
public class GuestMainMenu extends MainMenu{
	
	/**
	 * Opens window in which you can register for available auctions, 
	 * check the list of your registered auctions, check the list of bought items,
	 * top up wallet
	 * @param guest
	 * @throws Exception
	 */
	public static void  Menu(Guest guest) throws Exception {
		
		
		
		Stage Menu = new Stage();
		Menu.setResizable(false);
		
		Alert TopUpErr = new Alert(Alert.AlertType.INFORMATION);
		
		TopUpErr.setTitle("Wallet Top Up error");
		TopUpErr.setHeaderText("Your amount is incorrect");
		TopUpErr.setContentText("Please input only number as your amount of top up");

		
		Button bought = new Button("My Items");
		Button myAuc = new Button("My Auctions");
		Button TopUp = new Button("Top up");
		TextField amount = new TextField();
		amount.setPromptText("Enter amount");
		Label wallet = new Label("Your wallet:");
		wallet.setFont(new Font("Arial", 20));
		String wal = Integer.toString(guest.getWallet());
		Label walletBal = new Label(wal);
		walletBal.setFont(new Font("Arial", 22));
		
		
		AnchorPane buttons = new AnchorPane();
		
		AnchorPane.setTopAnchor(myAuc, 25.0);
		AnchorPane.setBottomAnchor(myAuc, 150.0);
		AnchorPane.setLeftAnchor(myAuc, 100.0);
		AnchorPane.setRightAnchor(myAuc, 250.0);
		
		AnchorPane.setTopAnchor(bought, 25.0);
		AnchorPane.setBottomAnchor(bought, 150.0);
		AnchorPane.setLeftAnchor(bought, 250.0);
		AnchorPane.setRightAnchor(bought, 100.0);
		
		AnchorPane.setTopAnchor(wallet, 75.0);
		AnchorPane.setBottomAnchor(wallet, 100.0);
		AnchorPane.setLeftAnchor(wallet, 100.0);
		AnchorPane.setRightAnchor(wallet, 250.0);
		
		AnchorPane.setTopAnchor(walletBal, 75.0);
		AnchorPane.setBottomAnchor(walletBal, 100.0);
		AnchorPane.setLeftAnchor(walletBal, 250.0);
		AnchorPane.setRightAnchor(walletBal, 100.0);
		
		AnchorPane.setTopAnchor(amount, 125.0);
		AnchorPane.setBottomAnchor(amount, 50.0);
		AnchorPane.setLeftAnchor(amount, 100.0);
		AnchorPane.setRightAnchor(amount, 250.0);
		
		AnchorPane.setTopAnchor(TopUp, 125.0);
		AnchorPane.setBottomAnchor(TopUp, 50.0);
		AnchorPane.setLeftAnchor(TopUp, 250.0);
		AnchorPane.setRightAnchor(TopUp, 100.0);
		
		buttons.getChildren().addAll(myAuc, bought, wallet, walletBal, amount, TopUp);

		
		
		
		bought.setOnAction(e -> {
			try {
				ModalMyItems.MyItems(guest);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		TopUp.setOnAction(e ->{
			try { 
				 GuestMenuController.topUp(guest, Integer.parseInt(amount.getText()), walletBal);
				 } catch (NumberFormatException e1) { 
					 TopUpErr.showAndWait();
				 } 
		});
		

		FlowPane items = new FlowPane();
		
		
		/*
		 * This part reades registered auctions in method ReadRItems and manages them
		 * then reads all of the items from file and creates list of buttons in Gui
		 * by pressing them you can view basic information about item and register it for yourself
		 */
		guest.ReadRItems();
		ReadItems();
		for(Collection o : Itemslist) {
			Button p = new Button(o.GetName());
			items.getChildren().add(p);
			p.setOnAction(e ->{

			try {
				ModalRegisterAuction.RegAuc(o, guest);
				//
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			});
			
		}
		
		//----------------------------------------------------------//
		myAuc.setOnAction(e ->{
			try {
				GuestMenuController.myAuc(items, walletBal, guest);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			}
		);
		
		/*
		 * when menu is about to close, it calls a method that stores and updates information about users wallet
		 */
		Menu.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	try {
					GuestMenuController.UpdateWal(guest);
		    		
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		//----------------------------------------------------------//
		
		items.setVgap(15);
		items.setHgap(15);
		items.setPrefWrapLength(300);
		
		ScrollPane sp = new ScrollPane(items);
		sp.setStyle("-fx-background-color: #336699;");
		sp.setMaxHeight(100);
		sp.setMaxWidth(300);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		Label text = new Label("All auctions:");
		text.setFont(new Font("Arial", 22));
		
		GridPane left = new GridPane();
		left.setVgap(5);
		left.setHgap(5);
		left.add(text, 5, 15);
		
		
		BorderPane root = new BorderPane();
		root.setLeft(left);
		root.setTop(buttons);
		root.setCenter(sp);
		
		Scene scene = new Scene(root);
		Menu.setWidth(500);
		Menu.setHeight(400);
		Menu.setScene(scene);
		Menu.setTitle("Menu");
		Menu.showAndWait();
		
	}
}
