package Gui;

import java.io.IOException;

import Controllers.AdminMenuController;
import Products.Collection;
import Users.Admin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Window that represents menu for admins
 */
public class AdminMainMenu extends MainMenu{
	
	/**
	 * Opens a window in which admin can: register new admin,
	 * create new auction, view guests list and view current available auctions.
	 * @param guest
	 * @throws Exception
	 */
	public static void  Menu(Admin guest) throws Exception {
		Stage Menu = new Stage();
		Menu.setResizable(false);
		
		Button GList = new Button("Guests list");
		Button CrAdmin = new Button("New Admin");
		Button AdAuc = new Button("Add Auction");
		
		AnchorPane buttons = new AnchorPane();
		
		AnchorPane.setTopAnchor(GList, 25.0);
		AnchorPane.setBottomAnchor(GList, 150.0);
		AnchorPane.setLeftAnchor(GList, 100.0);
		AnchorPane.setRightAnchor(GList, 250.0);
		
		AnchorPane.setTopAnchor(CrAdmin, 25.0);
		AnchorPane.setBottomAnchor(CrAdmin, 150.0);
		AnchorPane.setLeftAnchor(CrAdmin, 250.0);
		AnchorPane.setRightAnchor(CrAdmin, 100.0);
		
		AnchorPane.setTopAnchor(AdAuc, 75.0);
		AnchorPane.setBottomAnchor(AdAuc, 100.0);
		AnchorPane.setLeftAnchor(AdAuc, 150.0);
		AnchorPane.setRightAnchor(AdAuc, 150.0);
		
		buttons.getChildren().addAll(GList, CrAdmin, AdAuc);
		
		GList.setOnAction(e1 ->{
			try {
				ModalGuestslist.Guests();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		CrAdmin.setOnAction(e1 ->{
			try {
				ModalSignup.SignupWindow("Admins.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		

		FlowPane items = new FlowPane();
		ReadItems();
		ReadGuests();
		for(Collection o : Itemslist) {
			Button p = new Button(o.GetName());
			items.getChildren().add(p);
			p.setOnAction(e ->{

			try {
				ItemPreview.preview(o);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			});
			
		}
		
		AdAuc.setOnAction(e1 ->{
			try {
				AdminMenuController.AdAuc(items);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		items.setVgap(15);
		items.setHgap(15);
		items.setPrefWrapLength(300);
		
		ScrollPane sp = new ScrollPane(items);
		sp.setStyle("-fx-background-color: #336699;");
		sp.setMaxHeight(100);
		sp.setMaxWidth(300);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		BorderPane root = new BorderPane();
		root.setTop(buttons);
		root.setCenter(sp);
		
		Scene scene = new Scene(root);
		Menu.setWidth(500);
		Menu.setHeight(400);
		Menu.setScene(scene);
		Menu.setTitle("Admin's Menu");
		Menu.showAndWait();
	}
}
