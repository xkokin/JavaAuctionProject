package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * class represents controller for signing up the user
 * 
 */
public class SignupController {
	
	/**
	 * this method, depending on input parameter fileReg registers user as guest or as admin
	 * @param fileReg - name of the file in which method registers an user
	 * Firstly, checks files with listed users and if there are user with identical name as input name - throws error
	 * Then, depending on the choice of fileReg, creates new line of data in file, 
	 * if choice is Guest, creates 2 files for Registered items and Bought items
	 * if choice is Admin, only writes his data in Admins.txt
	 * @param fileReg
	 * @param login
	 * @param logRep
	 * @param password
	 * @param passwordCheck
	 * @param Swindow
	 * @param pass
	 * @throws IOException
	 */
		public static void SignUp(String fileReg, TextField login, Alert logRep,
			TextField password, TextField passwordCheck, Stage Swindow, Alert pass) throws IOException {
			File dir = new File("D://PrVerPrjKokin");
			File file1 = new File(dir, "Guests.txt");
			FileReader GuestCheck = new FileReader(file1);
			File file2 = new File(dir, "Admins.txt");
			FileReader AdminsCheck = new FileReader(file2);
			Scanner scanG = new Scanner(GuestCheck);
			Scanner scanA = new Scanner(AdminsCheck);
			
			String log = login.getText();
			Boolean can = true; 
			String compare;

			
			if(scanA.hasNextLine() == true || scanG.hasNextLine() == true) {
				try{
				while(scanG.hasNextLine() == true){
					
					compare = scanG.next();
					//System.out.println(compare);
					if(compare.compareTo(log) == 0) {
						logRep.showAndWait();
						can = false;
						scanA.close();
						scanG.close();
						AdminsCheck.close();
						GuestCheck.close();
						return;
					}
					compare = scanG.nextLine();
					//System.out.println(compare);
				}
				while(scanA.hasNextLine() == true){
					
					compare = scanA.next();
					//System.out.println(compare);
					if(compare.compareTo(log) == 0) {
						logRep.showAndWait();
						can = false;
						scanA.close();
						scanG.close();
						AdminsCheck.close();
						GuestCheck.close();
						return;
					}
					compare = scanA.nextLine();
					//System.out.println(compare);
				}
				}
				catch(NoSuchElementException e1) {
					
				}
			}
			
			if(can) {
					compare = password.getText();
					if(compare.compareTo(passwordCheck.getText()) == 0) {
						try {
						if(fileReg.compareTo("Admins.txt") == 0) {
							FileWriter userReg = new FileWriter(file2, true);
							userReg.append(log + " " + password.getText() + "\n");
							userReg.close();
						}
						else if(fileReg.compareTo("Guests.txt") == 0) {
							FileWriter userReg = new FileWriter(file1, true);
							userReg.append(log + " " + password.getText() + " 0\n");
							userReg.close();
						}
						
						
						String newB = log + "B.txt";
						File newBought = new File(dir, newB);
						if(newBought.exists() == false) {
							newBought.createNewFile();
						}
						String newR = log + "R.txt";
						File newReg = new File(dir, newR);
						if(newReg.exists() == false) {
							newReg.createNewFile();
						}
						
						GuestCheck.close();
						AdminsCheck.close();						

						
						scanA.close();
						scanG.close();
						Swindow.close();
						}
						catch(IOException e1) {
							System.out.println(e1);
						}

					}
					else if (compare.compareTo(passwordCheck.getText()) != 0){
						pass.showAndWait();
					}				
			}
		}
}
