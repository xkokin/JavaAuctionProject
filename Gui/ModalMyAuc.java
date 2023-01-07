package Gui;





import java.util.ArrayList;

import Controllers.ManageItem;
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
 * Modal window with registered auctions
 */
public class ModalMyAuc {
	
	/**
	 * Opens Modal window and shows list of the guest's registered auctions
	 *	if guest missed the auction or didn't join it in 2 minutes after button activated, he will be unregistered
	 * @param guest
	 * @throws Exception
	 */
		public static void MyAuc(Guest guest) throws Exception{			
			
			
			Stage MyAuc = new Stage();
			MyAuc.setResizable(false);
			MyAuc.initModality(Modality.APPLICATION_MODAL);
			
			FlowPane box = new FlowPane();
			box.setVgap(15);
			box.setHgap(15);
			box.setPrefWrapLength(200);
			
			ArrayList<Collection> toDelete = new ArrayList<Collection>();
			for(Collection o : guest.GetRegistered()) {
				Button p = new Button(o.GetName());
				if(o.IsStarted() == false) o.StartTimer();
				if(o.GetSchedule() >0)p.setDisable(true);
				if(o.GetSchedule() == 0)p.setDisable(false);
				if(o.IsEnded()) {
					ManageItem.DeleteReg(o, guest);
					toDelete.add(o);
				}
				box.getChildren().add(p);
				
				
				p.setOnAction(e ->{
	
				try {
					ModalAuction j = new ModalAuction();
					j.StartAuc(o, guest);
					MyAuc.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				});
				
			}
			for(Collection e : toDelete) {
				guest.GetRegistered().remove(e);
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
			MyAuc.setTitle("My Auctions");
			MyAuc.showAndWait();
		}
}
