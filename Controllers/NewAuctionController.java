package Controllers;

import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Controller for creating a new auction
 * */
public class NewAuctionController {
	/**
	 * Checks if input time is correct
	 * @param time
	 * @return
	 */
	public static boolean checkTime(String time) {
		if(time.length() != 5) return true;
		
		if (Character.isDigit(time.charAt(0)) && Character.isDigit(time.charAt(1)) &&
				Character.isDigit(time.charAt(3)) && Character.isDigit(time.charAt(4))
				&& time.charAt(2) == ':')return true;
		
		
		return false;
	}
	/**
	 * Checks if input collection is correct
	 * @param col
	 * @return
	 */
	public static boolean checkCollection(String col) {
		if(col.compareTo("BAYC") ==0||col.compareTo("CP")==0) return true;
		return false;
	}
	/**
	 * Method first of all checks input data to be correct
	 * then checks if auction with such name already exists
	 * if all of the checks is passed, auction will be stored as a new line with data in Items.txt
	 * @param name
	 * @param collection
	 * @param price
	 * @param time
	 * @param alEx
	 * @param wrDa
	 * @param stage
	 * @throws IOException
	 */
	public static void Reg(String name, String collection, String price,
			String time, Alert alEx, Alert wrDa, Stage stage) throws IOException {
		File dir = new File("D://PrVerPrjKokin");
		File file = new File(dir, "Items.txt");
		FileWriter writer = new FileWriter(file, true);
		FileReader reader = new FileReader(file);
		Scanner scan = new Scanner(reader);
		String str = new String();
		
		
		try{
			Integer.parseInt(price);
			
		
		} catch (NumberFormatException e1) {  //vlastna vynimka
			wrDa.showAndWait();
			reader.close();
			scan.close();
			writer.close();
			//stage.close();
			return;
		} 
		if(checkTime(time) == false || checkCollection(collection) == false) {
			wrDa.showAndWait();
			
			reader.close();
			scan.close();
			writer.close();
			//stage.close();
			return;
		}
		
		while(scan.hasNext() != false) {
			str = scan.next();
			str = scan.next();
			str = str.trim();
			if(str.compareTo(name)==0) {
				alEx.showAndWait();
				reader.close();
				scan.close();
				writer.close();
				//stage.close();
				return;
			}
			str=scan.nextLine();
		}
		scan.close();
		
		writer.write("\n" + collection + " " + name+ " "+price+" "+time);
		writer.close();
		
		stage.close();
	}
}
