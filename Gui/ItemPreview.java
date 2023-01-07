package Gui;

import Products.Collection;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Modal window with information about certain item
 */
public class ItemPreview {
	
	/**
	 * Opens modal window and shows information about item
	 * such as Name, Collection, Starting price, current price(or last bid), time that was scheduled for the auction
	 * 
	 * @param item
	 */
	public static void preview(Collection item) {
		Stage Reg = new Stage();
		Reg.setResizable(false);
		Reg.initModality(Modality.APPLICATION_MODAL);
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
		
		int priceInt = item.GetFprice();
		int lastp = item.GetPrice();
		String i = Integer.toString(priceInt);
		String l = Integer.toString(lastp);
		Label p = new Label("Start Price:");
		p.setFont(new Font("Arial", 20));
		Label price = new Label(i);
		price.setFont(new Font("Arial", 20));
		Label lp = new Label("Last Price:");
		lp.setFont(new Font("Arial", 20));
		Label lastprice = new Label(l);
		lastprice.setFont(new Font("Arial", 20));
		Label t = new Label("Scheduled:");
		t.setFont(new Font("Arial", 20));
		Label time = new Label(item.GetTime());
		time.setFont(new Font("Arial", 20));
		
		
		root.add(n, 5, 5);
		root.add(name, 15,  5);
		root.add(c, 5, 15);
		root.add(collection,  15, 15);
		root.add(p, 5, 25);
		root.add(price, 15, 25);
		root.add(lp, 5, 35);
		root.add(lastprice, 15, 35);
		root.add(t, 5, 45);
		root.add(time, 15, 45);
		
		
		
		Scene scene = new Scene(root);
		Reg.setWidth(500);
		Reg.setHeight(400);
		Reg.setScene(scene);
		Reg.setTitle(item.GetName() + " Preview");
		Reg.showAndWait();
	}
}
