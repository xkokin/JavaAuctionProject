package Gui;

import Products.Collection;
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
 * Modal window for bought items
 */
public class ModalMyItems {
	
	/**
	 * opens modal window with a list of guest's bought items(won auctions)
	 * @param guest
	 * @throws Exception
	 */
	public static void MyItems(Guest guest) throws Exception {
		Stage Items = new Stage();
		Items.setResizable(false);
		Items.initModality(Modality.APPLICATION_MODAL);
		
		FlowPane box = new FlowPane();
		box.setVgap(15);
		box.setHgap(15);
		box.setPrefWrapLength(200);
		
		guest.ReadBItems();
		
		for(Collection o : guest.GetBought()) {
			Button p = new Button(o.GetName());
			box.getChildren().add(p);
			p.setOnAction(e ->{

			ItemPreview.preview(o);
			
			});
			
		}
		
		
		ScrollPane sp = new ScrollPane(box);
		sp.setStyle("-fx-background-color: #336699;");
		sp.setMaxHeight(200);
		sp.setMaxWidth(200);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		
		Scene scene = new Scene(sp);
		Items.setWidth(250);
		Items.setHeight(250);
		Items.setScene(scene);
		Items.setTitle("My Items");
		Items.showAndWait();
		
		
	}
}
