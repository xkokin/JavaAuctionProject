package Gui;

import Products.Collection;
import Users.Guest;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * 
 */
public class ViewGuest {
	/**
	 * Gui that show guest's info such as login, bought items, registered auctions
	 * @param guest
	 * @throws Exception
	 */
	public static void View(Guest guest) throws Exception {
		Stage Reg = new Stage();
		Reg.setResizable(false);
		Reg.initModality(Modality.APPLICATION_MODAL);
		
		GridPane root = new GridPane();
		
		root.setVgap(5);
		root.setHgap(5);
		
		Label n = new Label("Login:");
		n.setFont(new Font("Arial", 20));
		Label name = new Label(guest.GetLogin());
		name.setFont(new Font("Arial", 20));
		Label c = new Label("Password:");
		c.setFont(new Font("Arial", 20));
		Label collection = new Label(guest.GetPassword());
		collection.setFont(new Font("Arial", 20));
		Label r = new Label("Registered:");
		r.setFont(new Font("Arial", 20));
		Label b = new Label("Bought:");
		b.setFont(new Font("Arial", 20));
		
		FlowPane Rbox = new FlowPane();
		Rbox.setVgap(15);
		Rbox.setHgap(15);
		Rbox.setPrefWrapLength(200);
		
		/**
		 *	reads registered auctions, creates button for each registered auction
		 */
		guest.ReadRItems();
		
		
		for(Collection o : guest.GetRegistered()) {
			Button p = new Button(o.GetName());
		
			Rbox.getChildren().add(p);
			p.setOnAction(e ->{

			ItemPreview.preview(o);
			
			});
			
		}
		ScrollPane reg = new ScrollPane(Rbox);
		
		reg.setStyle("-fx-background-color: #336699;");
		reg.setMaxHeight(200);
		reg.setMaxWidth(200);
		reg.setHbarPolicy(ScrollBarPolicy.NEVER);
		reg.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		FlowPane Bbox = new FlowPane();
		Bbox.setVgap(15);
		Bbox.setHgap(15);
		Bbox.setPrefWrapLength(200);
		
		guest.ReadBItems();
		for(Collection o : guest.GetBought()) {
			Button p = new Button(o.GetName());
			Bbox.getChildren().add(p);
			p.setOnAction(e ->{

			ItemPreview.preview(o);
			
			});
			
		}
		ScrollPane bought = new ScrollPane(Bbox);
		
		bought.setStyle("-fx-background-color: #336699;");
		bought.setMaxHeight(200);
		bought.setMaxWidth(200);
		bought.setHbarPolicy(ScrollBarPolicy.NEVER);
		bought.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		root.add(n, 5, 5);
		root.add(name, 25,  5);
		root.add(c, 5, 15);
		root.add(collection,  25, 15);
		root.add(r, 5, 25);
		root.add(b, 25, 25);
		root.add(reg, 5, 30);
		root.add(bought, 25, 30);
		
		

		
		Scene scene = new Scene(root);
		Reg.setWidth(550);
		Reg.setHeight(400);
		Reg.setScene(scene);
		Reg.setTitle("Information about " + guest.GetLogin());
		Reg.showAndWait();
	}
}
