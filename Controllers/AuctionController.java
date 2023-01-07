package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Gui.MainMenu;
import Gui.ModalAuction;
import Products.Collection;
import Users.Guest;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * */
public class AuctionController {
	
	/**
	 * Bids up current item's price 
	 * Firstly, checks if input is number,
	 * then if guest can afford buying this item with such price,
	 * if everything goes ok, bids price up and stores data about bid in file of the auction 
	 * @param ModAuc
	 * @param bid
	 * @param walErr
	 * @param guest
	 * @param item
	 * @param lastBid
	 * @param output
	 * @param bidErr
	 * @return
	 * @throws IOException
	 */
	public static boolean BidUp(ModalAuction ModAuc, TextField bid, Alert walErr, Guest guest,
			Collection item, Label lastBid, TextArea output, Alert bidErr) throws IOException {
		try {
		int intValue = Integer.parseInt(bid.getText());
		 if(intValue > guest.getWallet() - item.GetPrice()) {
			 walErr.showAndWait();
			 return false;
		 }
		 int newPrice = intValue+ item.GetPrice();
		 
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, item.GetName() + ".txt");
		FileWriter writer = new FileWriter(file, true); 
		
		writer.write(guest.GetLogin() + " " + newPrice + "\n");
		
		 
		 item.SetPrice(newPrice);
		 String np = Integer.toString(item.GetPrice());
		 lastBid.setText(np);
		 output.appendText("Price has changed. New price is " + item.GetPrice() + "\n");
		 //guest.TopUpwallet(-intValue);
		 item.setWinner(guest, true, null);
		 ModAuc.ResetBidTimer(11);
		 ModAuc.IteratePos();
		 writer.close();
		 
		 return true;
		 } catch (NumberFormatException e1) { 
		 bidErr.showAndWait();  //vlastna vynimka
		 return false;
		 } 
		
	}
	
	//---------------------------------------------------------
	/**
	 * * Setting up two timers: 10 minutes and 10 seconds,
	 * timer for 10 minutes starts instantly, 
	 * timer for 10 seconds will be started with the first bid up
	 *  if any guest does not bid up in 2 minutes from the beginning, auction ends
	 *  after every bid up that is noticed timer fo 10 seconds restartes 
	 *  when timers are over, system will delete item from available auctions, registered auction list of every participant,
	 *  and will put it in the list of bought items of the winner
	 *  if there are no winner(only possible when nobody has made a bid up) the item will be removed from registered list of participants,
	 *  but will remain in the list of available auctions 
	 * @param Auc
	 * @param guest
	 * @param item
	 * @param AucTime
	 * @param BidTime
	 * @param output
	 * @param BidUp
	 * @param lastBid
	 */
	public static void StartTimers(ModalAuction Auc, Guest guest, Collection item, 
			Label AucTime, Label BidTime, TextArea output, Button BidUp, Label lastBid) {
		Timer BidTimer = new Timer();
		Timer AucTimer = new Timer();
		
		
		
		TimerTask taskAuc = new TimerTask() {
			int counterA = 600;
			String time;
			
			
			public void run() {
				Platform.runLater(new Runnable() {
		            public void run() {
		            	if(counterA > 0) {
		            		
		            		time = Integer.toString(counterA);
		            		AucTime.setText(time);
		            		counterA--;
		            		if(counterA == 480 && Auc.GetPos() == 0) {
		            			output.appendText("Item's not sold\n");
		            			guest.GetRegistered().remove(item);
		            			try {
									ManageItem.DeleteReg(item, guest);
								} catch (IOException e) {
									e.printStackTrace();
								}
								time = Integer.toString(Auc.GetBidTimer());
								BidTime.setText(time);
								
								BidTimer.cancel();
								AucTimer.cancel();
								BidUp.setDisable(true);
		            		}
		            	}
		            	else {
		            		output.appendText("Time's up for the auction\n");
		            		if(item.getWinner() != null) {
		            			if(item.getWinner().GetLogin().compareTo(guest.GetLogin())==0) {
									output.appendText("Item " + item.GetName() + " has been sold to the " + item.getWinnerName());
									MainMenu.GetItemslist().remove(item);
									guest.TopUpwallet(-item.GetPrice());
									guest.Addtocollection(item);
									
									try {
										ManageItem.StoreBought(guest, item);
										ManageItem.DeleteBought(item);
										
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								else {
									output.appendText("Item " + item.GetName() + " has been sold to the " + item.getWinnerName());
									MainMenu.GetItemslist().remove(item);
									
								}
							}
		            		else {
		            			output.appendText("Item's not sold\n");
		            		}
		            		
		            		guest.GetRegistered().remove(item);
		            		try {
								ManageItem.DeleteReg(item, guest);
							} catch (IOException e) {
								e.printStackTrace();
							}
		            		time = Integer.toString(Auc.GetBidTimer());
		            		AucTime.setText(time);
		            		
		            		AucTimer.cancel();
		            		BidTimer.cancel();
		            		
		            		BidUp.setDisable(true);
		            	}
		            }
				});
			}
		};
		//-------------------------------------------------------//
		
		TimerTask taskBid = new TimerTask() {
			
			String time;
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		            	if(Auc.GetBidTimer() > 0) {
							time = Integer.toString(Auc.GetBidTimer());
							BidTime.setText(time);
							if(Auc.GetPos() != 0)Auc.ResetBidTimer(Auc.GetBidTimer()-1);
							//-------------------------------
							//every second checks file for new bids
							File dir = new File("D://PrVerPrjKokin");
							File file = new File(dir, item.GetName() + ".txt");
							if (file.exists() == false) {
								try {
									if(file.createNewFile() == true) System.out.println("File"+ item.GetName()+".txt has been created");
									else System.out.println("Failed to create file");
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							FileReader reader;
							
								try {
									reader = new FileReader(file);
									Scanner scan = new Scanner(reader);
									for(int i = 0; i< Auc.GetPos(); i++) scan.nextLine();
									while(scan.hasNext() != false) {
										if(scan.hasNextLine() == true) {
											String name = scan.next();
											name = name.trim();
											String newPrice = scan.next();
											newPrice.trim();
											item.setWinner(guest, false, name);
											Auc.ResetBidTimer(10);
											
											lastBid.setText(newPrice);
											item.SetPrice(Integer.parseInt(newPrice));
											output.appendText("User " +name +" has bid up the price to " +newPrice + "\n");
											Auc.IteratePos();
										}
									}
									scan.close();
									reader.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							

							//-------------------------------
						}
						else {
							output.appendText("Time's up for the new bid\n");
							if(item.getWinner() != null) {
								if(item.getWinner().GetLogin().compareTo(guest.GetLogin())==0) {
									output.appendText("Item " + item.GetName() + " has been sold to the " + item.getWinnerName());
									MainMenu.GetItemslist().remove(item);
									guest.TopUpwallet(-item.GetPrice());
									guest.Addtocollection(item);
									
									try {
										ManageItem.StoreBought(guest, item);
										ManageItem.DeleteBought(item);
										
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								else {
									output.appendText("Item " + item.GetName() + " has been sold to the " + item.getWinnerName());
									MainMenu.GetItemslist().remove(item);
									
								}
							}
							else {
								output.appendText("Item's not sold\n");
							}		
							guest.GetRegistered().remove(item);
							try {
								ManageItem.DeleteReg(item,guest);
							} catch (IOException e) {
								e.printStackTrace();
							}
							time = Integer.toString(Auc.GetBidTimer());
							BidTime.setText(time);
							
							BidTimer.cancel();
							AucTimer.cancel();
							BidUp.setDisable(true);
						}
		            }
		        });
		    }
		};
		AucTimer.scheduleAtFixedRate(taskAuc, 0, 1000);
		BidTimer.scheduleAtFixedRate(taskBid, 0, 1000);
	}
	
}
