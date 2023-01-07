package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Products.Collection;
import Users.Guest;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Controller to manage item in files 
 */
public class ManageItem {
	/**
	 * Writes item to the file with guest's bought items
	 * @param guest
	 * @param item
	 * @throws IOException
	 */
	public static void StoreBought(Guest guest, Collection item) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, guest.GetLogin() + "B.txt");
		FileWriter writer = new FileWriter(file, true);
		
		if(item.GetCollection().compareTo("Bored Apes Yacht Club")==0)writer.write("BAYC " + item.GetName()+ " "+item.GetFprice()+" "+item.GetPrice()+"\n");
		else if (item.GetCollection().compareTo("Crypto Punk")==0)writer.write("CP " + item.GetName()+ " "+item.GetFprice()+" "+item.GetPrice()+"\n");
		writer.close();
		return;
	}
	/**
	 * Writes item to the file with guest's registered auctions
	 * @param guest
	 * @param item
	 * @throws IOException
	 */
	public static void StoreReg(Guest guest, Collection item) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, guest.GetLogin() + "R.txt");
		FileWriter writer = new FileWriter(file, true);
		
		if(item.GetCollection().compareTo("Bored Apes Yacht Club")==0)writer.write("BAYC " + item.GetName()+ " "+item.GetFprice()+" "+item.GetTime()+"\n");
		else if (item.GetCollection().compareTo("Crypto Punk")==0)writer.write("CP " + item.GetName()+ " "+item.GetFprice()+" "+item.GetTime()+"\n");
		writer.close();
		return;
	}
	
	/**
	 * Deletes item from Items.txt after auction,
	 * deleting is completed by rewriting file Items.txt without certain auction(line)
	 * @param item
	 * @throws IOException
	 */
	public static void DeleteBought(Collection item) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File fileB = new File(dir, "Items.txt");
		
		
		
		FileReader readerB = new FileReader(fileB);
		
		Scanner scanB = new Scanner(readerB);
		
		
		
		String cmp = new String();
		String cntrl = new String();
		if(item.GetCollection().compareTo("Bored Apes Yacht Club") == 0)cmp = "BAYC " + item.GetName() + 
				" " + item.GetFprice()+ " " + item.GetTime(); 
		else if( item.GetCollection().compareTo("Crypto Punk") == 0) cmp = "CP " + item.GetName() + 
				" " + item.GetFprice()+ " " + item.GetTime(); 
		
		ArrayList<String> tempB = new ArrayList<String>();
		while(scanB.hasNextLine() != false) {
			cntrl = scanB.nextLine();
			if(cntrl.compareTo(cmp) == 0)continue;
			else tempB.add(cntrl);
		}
		scanB.close();
		readerB.close();
		FileWriter writerB = new FileWriter(fileB,false);
		for(String i : tempB) {
			writerB.write(i + "\n");
		}
		writerB.close();
		
		//-------------------------------------------
		
	}
	/**
	 * Deletes item from guest's registered auctions list after auction, or after the moment guest missed the auction
	 * deleting is completed by rewriting the file without certain auction(line)
	 * @param item
	 * @param guest
	 * @throws IOException
	 */
	public static void DeleteReg(Collection item, Guest guest) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File fileR = new File(dir, guest.GetLogin() + "R.txt");
		FileReader readerR = new FileReader(fileR);
		Scanner scanR = new Scanner(readerR);
		
		ArrayList<String> tempR = new ArrayList<String>();
		
		String cmp = new String();
		String cntrl = new String();
		if(item.GetCollection().compareTo("Bored Apes Yacht Club") == 0)cmp = "BAYC " + item.GetName() + 
				" " + item.GetFprice()+ " " + item.GetTime(); 
		else if( item.GetCollection().compareTo("Crypto Punk") == 0) cmp = "CP " + item.GetName() + 
				" " + item.GetFprice()+ " " + item.GetTime(); 
		
		
		while(scanR.hasNextLine() != false) {
			cntrl = scanR.nextLine();
			if(cntrl.compareTo(cmp) == 0)continue;
			else tempR.add(cntrl);
		}
		scanR.close();
		readerR.close();
		FileWriter writerR = new FileWriter(fileR,false);
		for(String i : tempR) {
			writerR.write(i + "\n");
		}
		writerR.close();
		
	}
}
