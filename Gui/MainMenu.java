package Gui;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Products.Collection;
import Products.NFTFactory;
import Products.NFTtypes;
import Users.Guest;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * class that containes lists of the items and guests that are registered in the system
 * class has methods to read items and guests from files and store them in ArrayLists 
 * */
public class MainMenu {

	/**
	 * available auctions
	 */
	static ArrayList<Collection> Itemslist = new ArrayList<Collection>();
	/**
	 * all of the registered guessts
	 */
	static ArrayList<Guest> guests = new ArrayList<Guest>();
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Collection> GetItemslist(){
		return Itemslist;
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Guest> GetGuestslist(){
		return guests;
	}
	
	/**
	 * * Reads items from Items.txt and adds them to the list 
	 * @throws Exception
	 */
	public static void ReadItems()throws Exception{
		if (Itemslist.isEmpty() == false) Itemslist.clear();
		
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, "Items.txt");
		if (file.exists() == false) {
			if(file.createNewFile() == true) System.out.println("File Items.txt has been created");
			else System.out.println("File Items.txt hasn't been created");
		}
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
			i.SetPrice(val);
			i.SetFPrice(val);
			
			str = scan.next();
			str = str.trim();
			i.SetTime(str);
			Itemslist.add(i);
			}	
		}
		reader.close();
		scan.close();
		
		return;
	}
	/**
	 *  Reads Guests from the list Guests.txt and adds them to the list 
	 * @throws Exception
	 */
	public static void ReadGuests()throws Exception{
		if(guests.isEmpty() == false) guests.clear();
		
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, "Guests.txt");
		if (file.exists() == false) {
			if(file.createNewFile() == true) System.out.println("File Guests.txt has been created");
			else System.out.println("File Guests.txt hasn't been created");
		}
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		String log = new String();
	
		
		while(scan.hasNext() == true) {
			Guest i = new Guest();
			
			log = scan.next();
			System.out.print(log+" ");
			i.SetLogin(log);
			
			log = scan.next();
			log = log.trim();
			System.out.println(log);
			i.SetPassword(log);
			
			log = scan.nextLine();
			log = log.trim();
			System.out.print(log + " ");
			i.TopUpwallet(Integer.parseInt(log));
			guests.add(i);
				
		}
		   
		reader.close();
		scan.close();
		
		return;
	}
}
