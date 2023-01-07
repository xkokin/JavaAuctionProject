package Gui;




import java.io.IOException;


import Controllers.AuctionController;

import Products.Collection;
import Users.Guest;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Modal window in which auction will be
 * 
 */
public class ModalAuction {
	/**
	 * counter for bid timer 
	 */
	private int counter = 10;
	/**
	 * position of the line in file of the auction
	 */
	private int position = 0;
	
	public void IteratePos() {
		this.position++;
	}
	
	public int GetPos() {
		return this.position;
	}
	
	public void ResetBidTimer(int num) {
		this.counter = num;
	}
	public int GetBidTimer() {
		return this.counter;
	}
	
	/**
	 * Opens modal window in which all of the data about current auction is written
	 * Such as timers, current price and history of the bids
	 * @param item
	 * @param guest
	 * @throws Exception
	 */
	public void StartAuc(Collection item, Guest guest) throws Exception{
			
			Stage Auc = new Stage();
			AnchorPane root = new AnchorPane();
			
			Label BidTime = new Label("00:00");
			BidTime.setFont(new Font("Arial", 22));
			Label AucTime = new Label("00:00");
			AucTime.setFont(new Font("Arial", 22));
			
			Label time1 = new Label("Auction will end in: ");
			time1.setFont(new Font("Arial", 22));
			Label time2 = new Label ("Time left for new bid: ");
			time2.setFont(new Font("Arial", 22));
			
			TextField bid = new TextField();
			bid.setPromptText("Enter bid");
			
			String price = new String();
			price = Integer.toString(item.GetPrice());
			Label lastBid = new Label(price);
			lastBid.setFont(new Font("Arial", 22));
			Label lastBidText = new Label("Current price: ");
			lastBidText.setFont(new Font("Arial", 22));
			
			TextArea output = new TextArea();

			output.setEditable(false);
			
			Button BidUp = new Button("Bid Up");
			//----------------------------------------------------------//
			
			Alert bidErr = new Alert(Alert.AlertType.INFORMATION);
			
			bidErr.setTitle("Bid set up error");
			bidErr.setHeaderText("Your bid is incorrect");
			bidErr.setContentText("Please input only number for your bid");
			
			Alert walErr = new Alert(Alert.AlertType.INFORMATION);
			
			walErr.setTitle("Bid set up error");
			walErr.setHeaderText("You can't set this bid");
			walErr.setContentText("There are not enough money in your wallet");
			
			Alert AucErr = new Alert(Alert.AlertType.INFORMATION);
			
			AucErr.setTitle("Auction closing error");
			AucErr.setHeaderText("You can't close auction now");
			AucErr.setContentText("Please, wait till the end of auction to close this window");
			
			//----------------------------------------------------------//
			
			AuctionController.StartTimers(this, guest, item, AucTime, BidTime, 
					output, BidUp, lastBid);
			
			//----------------------------------------------------------//
			Auc.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			    	if(guest.GetRegistered().contains(item) == true) {
			    		event.consume();
			    		AucErr.showAndWait();
			    	}
			    }
			});
			//-------------------------------------------------------//
			
			BidUp.setOnAction(e ->{
				try {
					AuctionController.BidUp(this,bid, walErr, guest, item, lastBid, output, bidErr);
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			});
			//------------------------------------------------------------//
			
			AnchorPane.setTopAnchor(time1, 50.0);
			AnchorPane.setLeftAnchor(time1, 150.0);
			AnchorPane.setRightAnchor(time1, 150.0);
			
			AnchorPane.setTopAnchor(AucTime, 50.0);
			AnchorPane.setLeftAnchor(AucTime, 400.0);
			AnchorPane.setRightAnchor(AucTime, 50.0);
			
			AnchorPane.setTopAnchor(time2, 100.0);
			AnchorPane.setLeftAnchor(time2, 150.0);
			AnchorPane.setRightAnchor(time2, 150.0);
			
			AnchorPane.setTopAnchor(BidTime, 100.0);
			AnchorPane.setLeftAnchor(BidTime, 400.0);
			AnchorPane.setRightAnchor(BidTime, 50.0);
			
			AnchorPane.setTopAnchor(bid, 150.0);
			AnchorPane.setLeftAnchor(bid, 150.0);
			AnchorPane.setRightAnchor(bid, 250.0);
			
			AnchorPane.setTopAnchor(BidUp, 150.0);
			AnchorPane.setLeftAnchor(BidUp, 300.0);
			AnchorPane.setRightAnchor(BidUp, 50.0);
			
			AnchorPane.setTopAnchor(lastBidText, 200.0);
			AnchorPane.setLeftAnchor(lastBidText, 150.0);
			AnchorPane.setRightAnchor(lastBidText, 150.0);
			
			AnchorPane.setTopAnchor(lastBid, 200.0);
			AnchorPane.setLeftAnchor(lastBid, 300.0);
			AnchorPane.setRightAnchor(lastBid, 50.0);
			
			AnchorPane.setTopAnchor(output, 250.0);
			AnchorPane.setBottomAnchor(output, 50.0);
			AnchorPane.setLeftAnchor(output, 50.0);
			AnchorPane.setRightAnchor(output, 50.0);
			
			
			root.getChildren().addAll(time1, AucTime, time2, BidTime, bid, BidUp, lastBidText, lastBid, output);
			//------------------------------------------------------------//
			Scene scene = new Scene(root, 450, 500);
			
			Auc.setTitle(item.GetName());
			Auc.setScene(scene);
			Auc.setHeight(500);
			Auc.setWidth(550);
			Auc.setResizable(false);
			Auc.initModality(Modality.APPLICATION_MODAL);
			Auc.showAndWait();
			
			
			
			
		
	}
}

