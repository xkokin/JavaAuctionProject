package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Gui.MainMenu;
import Gui.ModalMyAuc;
import Gui.ModalRegisterAuction;
import Products.Collection;
import Users.Guest;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Guest Main Menu controller
 * */
public class GuestMenuController {
	/**
	 * tops up guest's wallet on amount of input 
	 * @param guest
	 * @param sum
	 * @param wallet
	 */
	public static void topUp(Guest guest, int sum, Label wallet) {
		guest.TopUpwallet(sum);
		String np = Integer.toString(guest.getWallet());
		wallet.setText(np);
	}
	/**
	 * opens window with registered auctions for guest and after window closes it checks for the changes in items list 
	 * if there are some changes it will delete auction that has been sold or auction that some admin has added moment ago
	 * @param items
	 * @param wallet
	 * @param guest
	 * @throws Exception
	 */
	public static void myAuc(FlowPane items, Label wallet, Guest guest) throws Exception {
		ModalMyAuc.MyAuc(guest);
		String wall = Integer.toString(guest.getWallet());
		wallet.setText(wall);
		items.getChildren().clear();
		MainMenu.ReadItems();
		for(Collection o : MainMenu.GetItemslist()) {
			Button p = new Button(o.GetName());
			items.getChildren().add(p);
			p.setOnAction(e1 ->{
			try {
				ModalRegisterAuction.RegAuc(o, guest);
				//
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			});
			
		}
	}
	/**
	 * Rewrites file with Guests and corrects the wallet
	 * @param guest
	 * @throws IOException
	 */
	public static void UpdateWal(Guest guest) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, "Guests.txt");
		
		
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		
		
		String cmp = new String();
		String cntrl = new String();
		cmp = guest.GetLogin(); 
		
		
		ArrayList<String> tempB = new ArrayList<String>();
		while(scan.hasNext() != false) {
			cntrl = scan.next();
			if(cntrl.compareTo(cmp) == 0) {
				tempB.add(cntrl +" "+ guest.GetPassword() + " "+ guest.getWallet());
				scan.nextLine();
			}
			else {
				tempB.add(cntrl+ scan.nextLine());
			}
		}
		scan.close();
		reader.close();
		FileWriter writer = new FileWriter(file,false);
		for(String i : tempB) {
			writer.write(i + "\n");
		}
		writer.close();
	}
}


