package Gui;


import Users.Guest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Modal window with list of regisstered guests
 * 
 */
public class ModalGuestslist {
	/**
	 * Opens modal window and Creates button for each guest that is registered in system 
	 * @throws Exception
	 */
	public static void Guests() throws Exception{
		Stage MyAuc = new Stage();
		MyAuc.setResizable(false);
		MyAuc.initModality(Modality.APPLICATION_MODAL);
		
		FlowPane box = new FlowPane();
		box.setVgap(15);
		box.setHgap(15);
		box.setPrefWrapLength(200);
		
		
		for(Guest o : MainMenu.GetGuestslist()) {
			Button p = new Button(o.GetLogin());
			box.getChildren().add(p);
			p.setOnAction(e ->{

			try {
				
				ViewGuest.View(o);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			});
			
		}
		
		
		ScrollPane sp = new ScrollPane(box);
		sp.setStyle("-fx-background-color: #336699;");
		sp.setMaxHeight(200);
		sp.setMaxWidth(200);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		
		Scene scene = new Scene(sp);
		MyAuc.setWidth(250);
		MyAuc.setHeight(250);
		MyAuc.setScene(scene);
		MyAuc.setTitle("Guests list");
		MyAuc.showAndWait();
	}
}
