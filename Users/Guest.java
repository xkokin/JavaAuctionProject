package Users;


import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Scanner;

import Controllers.ManageItem;
import Products.*;

/**
 * @author HlibKokin
 * @version 13/05/2022
 *Objects from this class will take part in auctions  
 */

public class Guest extends User{

	
	private int wallet = 0;
	/**
	 * List of guest's registered auctions 
	 */
	private ArrayList<Collection> registered = new ArrayList<Collection>();
	/**
	 * List of guest's bought items
	 */
	private ArrayList<Collection> boughtItems = new ArrayList<Collection>();
	
	
	
	public void setTitul() {
		this.Titul = "Guest";
	}
	
	public Guest (String login, String password) {
		super(login, password);
		wallet = 0;
	}
	public Guest () {
		wallet = 0;
	}
	public void TopUpwallet(int num) {
		this.wallet +=num;
	}
	public int getWallet() {
		return this.wallet;
	}
	
	public void Addtocollection(Collection item) {
		this.boughtItems.add(item);
	}
	public void RegisterAuction(Collection item) {
		this.registered.add(item);
	}

	public ArrayList<Collection> GetRegistered() {
		return registered;
	}
	public ArrayList<Collection> GetBought(){
		return boughtItems;
	}
	
	
	/**
	 * This method Reads all bought items from guest's file 
	 * Firstly deleting all items from list boughtItems
	 * Then scanning each line, each line = item
	 * Using Factory creates item of an certain collection BAYC or CP
	 * And then adds it to the list
	 * @throws Exception
	 */
	public void ReadBItems()throws Exception{
		
		if (boughtItems.isEmpty() == false) boughtItems.clear();
		
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, this.GetLogin() + "B.txt");
		
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		String str = new String();
		
		while(scan.hasNext() == true) {
			str = scan.next();
			str = str.trim();
			//System.out.println(str);
			Collection i = null;
			NFTtypes type = NFTtypes.valueOf(str);
			
			
			i = NFTFactory.createNFT(type);
			if(i == null) {
				scan.close();
				reader.close();
				return;
			}else {
			str=scan.next();
			str = str.trim();
			i.SetName(str);
			str = scan.next();
			str = str.trim();
			int val = Integer.parseInt(str);
			i.SetFPrice(val);
			str = scan.next();
			str = str.trim();
			val = Integer.parseInt(str);
			
			i.SetPrice(val);
			
			boughtItems.add(i);
			}	
		}
		reader.close();
		scan.close();
		
		return;
	}
	
	/**
	 * Doing the same thing as
	 * @see Guest#ReadBItems()
	 * But reads registered auctions from a file
	 * And then start thread for timer for each registered auction 
	 * if timer equals or less than 0 the auction will be deleted from list and from the file
	 * @throws Exception
	 */
	public void ReadRItems()throws Exception{
		if (registered.isEmpty() == false) registered.clear();
		
		ArrayList<Collection> toRemove = new ArrayList<Collection>();
		
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, this.GetLogin() + "R.txt");
		
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		String str = new String();
		
		while(scan.hasNext() == true) {
			str = scan.next();
			str = str.trim();

			Collection i = null;
			
			NFTtypes type = NFTtypes.valueOf(str);
			i = NFTFactory.createNFT(type);
			
			if(i == null) {
				scan.close();
				reader.close();
				return;
			}else {
			str=scan.next();
			str = str.trim();
			i.SetName(str);
			str = scan.next();
			str = str.trim();
			int val = Integer.parseInt(str);
			i.SetFPrice(val);
			i.SetPrice(val);
			
			str = scan.next();
			str = str.trim();			
			i.SetTime(str);
			
			registered.add(i);
			}	
		}
		for(Collection i : registered) {
			i.SetSchedule();
			if (i.GetSchedule() <= 0) {
				ManageItem.DeleteReg(i, this);
				toRemove.add(i);
			}
			else i.StartTimer();
		}
		for(Collection o : toRemove) {
			registered.remove(o);
		}
		reader.close();
		scan.close();
		
		return;
	}
	
	
}
